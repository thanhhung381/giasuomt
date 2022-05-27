package giasuomt.demo.uploadfile.service;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.google.common.collect.Sets;

import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.person.repository.IRegisterAndLearnerRepository;
import giasuomt.demo.person.repository.ITutorRepository;
import giasuomt.demo.uploadfile.ultils.AwsClientS3;
import giasuomt.demo.uploadfile.ultils.FileUltils;

@Service
@Transactional
public class AvatarAndPublicAndPrivateRegisterAndLearnerAwsService extends AwsClientS3
		implements IAvatarAndPublicAndPrivateRegisterAndLearnerAwsService {

	@Autowired
	private IRegisterAndLearnerRepository iRegisterAndLearnerRepository;

	@Value("${amazon.registerandlearnerURL}")
	private String registerAndLearnerAvatarURL;

	@Value("${amazon.bucketnameregisterandlearner}")
	private String bucketNameRegisterAndLearnerAvatar;

	@Value("${amazon.registerandlearnerPrivateimgsURL}")
	private String registerAndLearnerPrivateimgsURL;

	@Value("${amazon.bucketnameRegisterandlearners}")
	private String bucketnamePrivateimgs;

	@Value("${amazon.registerandlearnerPublicimgsURL}")
	private String registerAndLearnerPublicimgsURL;

	@Value("${amazon.bucketnameRegisterandlearner}")
	private String bucketnamePublicimgs;

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

	public String uploadImageToAmazon(MultipartFile multipartFile, Long id) {
		try {

			String url = uploadMultipartFile(multipartFile, String.valueOf(id), bucketNameRegisterAndLearnerAvatar,
					registerAndLearnerAvatarURL);

			RegisterAndLearner registerAndLearner = iRegisterAndLearnerRepository.getOne(id);

			registerAndLearner.setAvatar(url);

			iRegisterAndLearnerRepository.save(registerAndLearner);

			return "Insert  Avatar successfully";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<String> findAll() {
		List<String> listObject = new LinkedList<>();

		ObjectListing iterables = client.listObjects(bucketNameRegisterAndLearnerAvatar);
		for (S3ObjectSummary os : iterables.getObjectSummaries()) {
			listObject.add(registerAndLearnerAvatarURL + os.getKey());
		}

		return listObject;
	}

	@Override
	public List<String> findAllPrivateImgs() {
		List<String> listObject = new LinkedList<>();

		ObjectListing iterables = client.listObjects(bucketnamePrivateimgs);
		for (S3ObjectSummary os : iterables.getObjectSummaries()) {
			listObject.add(registerAndLearnerPrivateimgsURL + os.getKey());
		}

		return listObject;
	}

	@Override
	public List<String> findAllPublicImgs() {
		List<String> listObject = new LinkedList<>();

		ObjectListing iterables = client.listObjects(bucketnamePublicimgs);
		for (S3ObjectSummary os : iterables.getObjectSummaries()) {
			listObject.add(registerAndLearnerPublicimgsURL + os.getKey());
		}

		return listObject;
	}

	@Override
	public void deleteByFileNameAndID(String urlFile) {
		try {

			client.deleteObject(bucketNameRegisterAndLearnerAvatar, urlFile.substring(urlFile.lastIndexOf('/') + 1));

			RegisterAndLearner registerAndLearner = iRegisterAndLearnerRepository
					.getOne(Long.parseLong(urlFile.substring(urlFile.lastIndexOf('/') + 1)));

			registerAndLearner.setAvatar(null);

			iRegisterAndLearnerRepository.save(registerAndLearner);

		} catch (AmazonServiceException e) {

			e.printStackTrace();
		}

	}

	@Override
	public String uploadImageToAmazonPrivateImgs(MultipartFile multipartFile, String filename) {
		try {

			String url = uploadMultipartFile(multipartFile, filename, bucketnamePrivateimgs,
					registerAndLearnerPrivateimgsURL);

			System.out.println(url);

			RegisterAndLearner registerAndLearner = iRegisterAndLearnerRepository.getOne(
					Long.parseLong(filename.substring(filename.lastIndexOf("/") + 1, filename.lastIndexOf("Private"))));

			Set<String> urlPrivateImgs = registerAndLearner.getPrivateImgs();

			urlPrivateImgs.add(url);

			List<String> converter = new LinkedList<>(urlPrivateImgs);

			converter = removeDuplicateElemet(converter);

			registerAndLearner.setPrivateImgs(Sets.newHashSet(converter));

			iRegisterAndLearnerRepository.save(registerAndLearner);

			return "Insert private img successfully";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String uploadImageToAmazonPubclicImgs(MultipartFile multipartFile, String filename) {
		try {

			String url = uploadMultipartFile(multipartFile, filename, bucketnamePublicimgs,
					registerAndLearnerPublicimgsURL);

			System.out.println(url);

			RegisterAndLearner registerAndLearner = iRegisterAndLearnerRepository.getOne(
					Long.parseLong(filename.substring(filename.lastIndexOf("/") + 1, filename.lastIndexOf("Public"))));

			Set<String> urlPublicImgs = registerAndLearner.getPublicImgs();

			urlPublicImgs.add(url);

			List<String> converter = new LinkedList<>(urlPublicImgs);

			converter = removeDuplicateElemet(converter);

			registerAndLearner.setPublicImgs(Sets.newHashSet(converter));

			iRegisterAndLearnerRepository.save(registerAndLearner);

			return "Insert public img successfully";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean checkExistObjectinS3(String name) {
		try {
			boolean flag = this.client.doesObjectExist(bucketNameRegisterAndLearnerAvatar, name);
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

			client.deleteObject(bucketnamePrivateimgs, urlFile.substring(urlFile.lastIndexOf('/') + 1));

			RegisterAndLearner registerAndLearner = iRegisterAndLearnerRepository.getOne(
					Long.parseLong(urlFile.substring(urlFile.lastIndexOf("/") + 1, urlFile.lastIndexOf("Private"))));

			Set<String> privateImgs = registerAndLearner.getPrivateImgs();

			privateImgs.remove(urlFile);

			registerAndLearner.setPrivateImgs(privateImgs);

			iRegisterAndLearnerRepository.save(registerAndLearner);

		} catch (AmazonServiceException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void deleteByFileNameAndIDPublicImgs(String urlFile) {
		try {

			client.deleteObject(bucketnamePublicimgs, urlFile.substring(urlFile.lastIndexOf('/') + 1));

			RegisterAndLearner registerAndLearner = iRegisterAndLearnerRepository.getOne(
					Long.parseLong(urlFile.substring(urlFile.lastIndexOf("/") + 1, urlFile.lastIndexOf("Public"))));

			Set<String> publicImgs = registerAndLearner.getPublicImgs();

			publicImgs.remove(urlFile);

			registerAndLearner.setPublicImgs(publicImgs);

			iRegisterAndLearnerRepository.save(registerAndLearner);

		} catch (AmazonServiceException e) {

			e.printStackTrace();
		}

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
	public boolean checkExistObjectPrivateInS3(String name) {
		try {
			boolean flag = this.client.doesObjectExist(bucketnamePrivateimgs, name);
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
			boolean flag = this.client.doesObjectExist(bucketnamePublicimgs, name);
			if (flag)
				return true;

		} catch (SdkClientException e) {

			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String updatePrivateImageToAmazon(MultipartFile multipartFile, String filename) {
		try {

			String url = uploadMultipartFile(multipartFile, filename, bucketnamePrivateimgs,
					registerAndLearnerPrivateimgsURL);

			return " Update private img successfully";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String updatePublicImageToAmazon(MultipartFile multipartFile, String filename) {
		try {

			String url = uploadMultipartFile(multipartFile, filename, bucketnamePublicimgs,
					registerAndLearnerPublicimgsURL);

			return " Update public img successfully";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
