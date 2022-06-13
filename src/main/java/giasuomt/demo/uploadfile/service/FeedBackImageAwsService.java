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

import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.tutorReview.model.TutorReview;
import giasuomt.demo.tutorReview.repository.ITutorReviewRepository;
import giasuomt.demo.uploadfile.ultils.AwsClientS3;
import giasuomt.demo.uploadfile.ultils.FileUltils;
import lombok.AllArgsConstructor;

@Service
@Transactional
public class FeedBackImageAwsService extends AwsClientS3 implements IFeedBackImageAwsService {

	@Autowired 
	private ITutorReviewRepository iTutorReviewRepository;
	
	@Value("${amazon.bucketnamePrivateFeedBackTutorReview}")
	private String bucketNamePrivate;
	
	@Value("${amazon.privateFeedBackTutorReviewURL}")
	private String urlPrivate;
	
	
	@Value("${amazon.bucketnamePublicFeedBackTutorReview}")
	private String bucketNamePublic;
	
	@Value("${amazon.publicFeedBackTutorReviewURL}")
	private String urlPublic;
	
	
	private void upploadPublicFile(String filename, File file, String bucketName) {
		client.putObject(
				new PutObjectRequest(bucketName, filename, file).withCannedAcl(CannedAccessControlList.PublicRead));
		
	}

	private String uploadMultipartFile(MultipartFile multipartFile, String nameFile, String bucketName, String url) {
		String imageURL = null;

		try {

			File file = FileUltils.convertMultiPathToFile(multipartFile);

			upploadPublicFile(nameFile, file, bucketName);

			file.delete();

			// urlAvatar "http://meomeo/"tutorAvatarURL
			imageURL = url.concat(nameFile);

			return imageURL;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@Override
	public String uploadImageToAmazon(MultipartFile multipartFile, String filename) {
		
		return null;
	}

	@Override
	public List<String> findAllPrivateImgs() {
		List<String> listObject = new LinkedList<>();

		ObjectListing iterables = client.listObjects(bucketNamePrivate);
		for (S3ObjectSummary os : iterables.getObjectSummaries()) {
			listObject.add(urlPrivate + os.getKey());
		}

		return listObject;
	}
	
	

	@Override
	public List<String> findAllPublicImgs() {
		List<String> listObject = new LinkedList<>();

		ObjectListing iterables = client.listObjects(bucketNamePublic);
		for (S3ObjectSummary os : iterables.getObjectSummaries()) {
			listObject.add(urlPublic + os.getKey());
		}

		return listObject;
	}

	@Override
	public String uploadImageToAmazonPrivateImgs(MultipartFile multipartFile, String filename) {
		try {
			String url = uploadMultipartFile(multipartFile, filename, bucketNamePrivate, urlPrivate);

			System.out.println(url);

			TutorReview tutorReview = iTutorReviewRepository.getOne(
					Long.parseLong(filename.substring(filename.lastIndexOf("/") + 1, filename.lastIndexOf("Private"))));

			Set<String> urlPrivateImgs = tutorReview .getPrivateFeedbackImgs();

			urlPrivateImgs.add(url);

			List<String> converter = new LinkedList<>(urlPrivateImgs);

			converter = removeDuplicateElemet(converter);

			tutorReview.setPrivateFeedbackImgs(Sets.newHashSet(converter));

			iTutorReviewRepository.save(tutorReview);

			return "Insert private img successfully";
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String uploadImageToAmazonPubclicImgs(MultipartFile multipartFile, String filename) {
		try {
			String url = uploadMultipartFile(multipartFile, filename, bucketNamePublic, urlPublic);

			System.out.println(url);

			TutorReview tutorReview = iTutorReviewRepository.getOne(
					Long.parseLong(filename.substring(filename.lastIndexOf("/") + 1, filename.lastIndexOf("Public"))));

			Set<String> urlPrivateImgs = tutorReview.getPublicFeedbackImgs();

			urlPrivateImgs.add(url);

			List<String> converter = new LinkedList<>(urlPrivateImgs);

			converter = removeDuplicateElemet(converter);

			tutorReview.setPublicFeedbackImgs(Sets.newHashSet(converter));

			iTutorReviewRepository.save(tutorReview);

			return "Insert public img successfully";
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean checkExistObjectPrivateInS3(String name) {
		try {
			boolean flag = this.client.doesObjectExist(bucketNamePrivate, name);
			if (flag)
				return true;

		} catch (SdkClientException e) {

			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkExistObjectPublicInS3(String name) {
		try {
			boolean flag = this.client.doesObjectExist(bucketNamePublic, name);
			if (flag)
				return true;

		} catch (SdkClientException e) {

			e.printStackTrace();
		}
		return false;
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
	public void deleteByFileNameAndIDPrivateImgs(String urlFile) {
		try {

			client.deleteObject(bucketNamePublic, urlFile.substring(urlFile.lastIndexOf('/') + 1));

			TutorReview tutorReview = iTutorReviewRepository.getOne(
					Long.parseLong(urlFile.substring(urlFile.lastIndexOf("/") + 1, urlFile.lastIndexOf("Private"))));

			Set<String> publicImgs = tutorReview.getPrivateFeedbackImgs();

			publicImgs.remove(urlFile);

			tutorReview.setPrivateFeedbackImgs(publicImgs);

			iTutorReviewRepository.save(tutorReview);

		} catch (AmazonServiceException e) {

			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteByFileNameAndIDPublicImgs(String urlFile) {
		try {

			client.deleteObject(bucketNamePublic, urlFile.substring(urlFile.lastIndexOf('/') + 1));

			TutorReview tutorReview = iTutorReviewRepository.getOne(
					Long.parseLong(urlFile.substring(urlFile.lastIndexOf("/") + 1, urlFile.lastIndexOf("Public"))));

			Set<String> publicImgs = tutorReview.getPublicFeedbackImgs();

			publicImgs.remove(urlFile);

			tutorReview .setPublicFeedbackImgs(publicImgs);

			iTutorReviewRepository.save(tutorReview);

		} catch (AmazonServiceException e) {

			e.printStackTrace();
		}
		
	}

	@Override
	public String updatePrivateImageToAmazon(MultipartFile multipartFile, String filename) {
		try {

			String url = uploadMultipartFile(multipartFile, filename, bucketNamePrivate, urlPrivate);

			return " Update private img successfully";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String updatePublicImageToAmazon(MultipartFile multipartFile, String filename) {
		try {

			String url = uploadMultipartFile(multipartFile, filename, bucketNamePublic, urlPublic);

			return " Update public img successfully";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
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
	
}
