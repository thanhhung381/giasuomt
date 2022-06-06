package giasuomt.demo.uploadfile.service;

import java.io.File;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.google.common.collect.Sets;

import giasuomt.demo.finance.model.JobFinance;
import giasuomt.demo.finance.repository.IJobFinanceRepository;
import giasuomt.demo.job.model.JobReview;
import giasuomt.demo.job.repository.IJobReviewRepository;
import giasuomt.demo.person.model.RegisterAndLearner;


import giasuomt.demo.uploadfile.ultils.AwsClientS3;
import giasuomt.demo.uploadfile.ultils.FileUltils;
import lombok.AllArgsConstructor;

@Service
@Transactional
public class FeedBackImageAwsService extends AwsClientS3 implements IFeedBackImageAwsService {

	@Value("${amazon.feedbackImageURL}")
	private String urlFeedBackImage;

	@Value("${amazon.bucketnamefeedbackImage}")
	private String bucketNameFeedBackImage;

	@Autowired
	private IJobReviewRepository iJobReviewRepository;

	private void uploadPulicFile(String filename, File file, String bucketName) {
		this.client.putObject(
				new PutObjectRequest(bucketName, filename, file).withCannedAcl(CannedAccessControlList.PublicRead));
	}

	private String uploadMutipartFile(MultipartFile multipartFile, String nameFile, String bucketName, String url) {
		String feedBackImageUrl = null;
		try {

			File file = FileUltils.convertMultiPathToFile(multipartFile);

			uploadPulicFile(nameFile, file, bucketName);

			file.delete();

			feedBackImageUrl = url.concat(nameFile);

			return feedBackImageUrl;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String uploadImageToAmazon(MultipartFile multipartFile, String fileName) {
		try {

			String url = uploadMutipartFile(multipartFile, fileName, bucketNameFeedBackImage, urlFeedBackImage);

			JobReview jobReview = iJobReviewRepository
					.getOne(Long.parseLong(url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("Review"))));

			Set<String> feedBackImgs = jobReview.getFeedbackImgs();

			feedBackImgs.add(url);

			List<String> converter = new LinkedList<>(feedBackImgs);

			converter = removeDuplicateElemet(converter);

			jobReview.setFeedbackImgs(Sets.newHashSet(converter));

			iJobReviewRepository.save(jobReview);

			return "Insert feedback  img successfully";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<String> findAll() {
		List<String> listObject = new LinkedList<>();

		ObjectListing iterables = client.listObjects(bucketNameFeedBackImage);
		for (S3ObjectSummary os : iterables.getObjectSummaries()) {
			listObject.add(urlFeedBackImage + os.getKey());
		}

		return listObject;
	}

	@Override
	public void deleteByFileNameAndID(String urlFile) {
		try {

			client.deleteObject(bucketNameFeedBackImage, urlFile.substring(urlFile.lastIndexOf('/') + 1));

			JobReview jobReview = iJobReviewRepository.getOne(
					Long.parseLong(urlFile.substring(urlFile.lastIndexOf('/') + 1, urlFile.lastIndexOf("Review"))));

			Set<String> feedBackImgs = jobReview.getFeedbackImgs();

			feedBackImgs.remove(urlFile);

			jobReview.setFeedbackImgs(feedBackImgs);

			iJobReviewRepository.save(jobReview);

		} catch (AmazonServiceException e) {

			e.printStackTrace();
		}

	}

	@Override
	public boolean checkExistIdOfT(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkExistObjectinS3(String name) {
		try {
			boolean flag = this.client.doesObjectExist(bucketNameFeedBackImage, name);
			if (flag)
				return true;

		} catch (SdkClientException e) {

			e.printStackTrace();
		}
		return false;
	}

	private List<String> removeDuplicateElemet(List<String> string) {
		List<String> temp = new LinkedList<>();

		for (int i = 0; i < string.size(); i++) {
			boolean check = false;
			for (int j = 0; j < i; j++) {
				if (string.get(i).contains(string.get(j))) {
					check = true;
					break;
				}
			}

			if (check == false) {

				for (int j = i + 1; j < string.size(); j++) {
					if (string.get(i).contains(string.get(j))) {

						string.remove(j);
						j--;
					}
				}
				temp.add(string.get(i));
			}

		}

		return temp;
	}

	@Override
	public String findAllByNameContainer(String name, List<String> containURLs) {
		List<String> findAllListContain = new LinkedList<>();
		for (String object : containURLs) {
			if (object.contains(name))
				findAllListContain.add(object);
		}

		Collections.sort(findAllListContain);

		if (findAllListContain.isEmpty())
			return null;

		return findAllListContain.get(findAllListContain.size() - 1);
	}

	@Override
	public String updateFeedBackToAmazon(MultipartFile file, String nameFile) {
		try {

			String url = uploadMutipartFile(file, nameFile, bucketNameFeedBackImage, urlFeedBackImage);

			return " Update feedback img successfully";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
