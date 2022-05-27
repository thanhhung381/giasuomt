package giasuomt.demo.uploadfile.service;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
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

import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.person.repository.ITutorRepository;
import giasuomt.demo.uploadfile.ultils.AwsClientS3;
import giasuomt.demo.uploadfile.ultils.FileUltils;
import giasuomt.demo.user.model.User;

@Service
@Transactional
public class AvatarAndPublicAndPrivateImgsTutorAwsService extends AwsClientS3
		implements IAvatarAndPublicAndPrivateImgsTutorAwsService {

	@Autowired
	private ITutorRepository iTutorRepository;

	@Value("${amazon.tutorAvatarURL}")
	private String tutorAvatarURL;

	@Value("${amazon.bucketnametutorAvatar}")
	private String bucketNameTutorAvatar;

	@Value("${amazon.tutorPrivateimgsURL}")
	private String tutorPrivateimgsURL;

	@Value("${amazon.bucketnamePrivateimgs}")
	private String bucketnamePrivateimgs;

	@Value("${amazon.tutorPublicimgsURL}")
	private String tutorPublicimgsURL;

	@Value("${amazon.bucketnamePublicimgs}")
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

	@Override
	public String uploadImageToAmazon(MultipartFile multipartFile, String tutorCode) {
		try {

			String url = uploadMultipartFile(multipartFile, tutorCode, bucketNameTutorAvatar, tutorAvatarURL);

			Tutor tutor = iTutorRepository.getOne(Long.parseLong(tutorCode));

			tutor.setAvatar(url);

			iTutorRepository.save(tutor);

			return "Insert  Avatar successfully";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<String> findAll() {
		List<String> listObject = new LinkedList<>();

		ObjectListing iterables = client.listObjects(bucketNameTutorAvatar);
		for (S3ObjectSummary os : iterables.getObjectSummaries()) {
			listObject.add(tutorAvatarURL + os.getKey());
		}

		return listObject;
	}

	@Override
	public void deleteByFileNameAndID(String urlFile) {
		try {

			client.deleteObject(bucketNameTutorAvatar, urlFile.substring(urlFile.lastIndexOf('/') + 1));

			Tutor tutor = iTutorRepository.getOne(Long.parseLong(urlFile.substring(urlFile.lastIndexOf('/') + 1)));

			tutor.setAvatar(null);

			iTutorRepository.save(tutor);

		} catch (AmazonServiceException e) {

			e.printStackTrace();
		}

	}

	@Override
	public boolean checkExistObjectinS3(String name) {
		try {
			boolean flag = this.client.doesObjectExist(bucketNameTutorAvatar, name);
			if (flag)
				return true;

		} catch (SdkClientException e) {

			e.printStackTrace();
		}
		return false;
	}

// private Imgs
	@Override
	public String uploadImageToAmazonPrivateImgs(MultipartFile multipartFile, String filename) {
		try {

			String url = uploadMultipartFile(multipartFile, filename, bucketnamePrivateimgs, tutorPrivateimgsURL);

			System.out.println(url);

			Tutor tutor = iTutorRepository.getOne(
					Long.parseLong(filename.substring(filename.lastIndexOf("/") + 1, filename.lastIndexOf("Private"))));

			Set<String> urlPrivateImgs = tutor.getPrivateImgs();

			urlPrivateImgs.add(url);

			List<String> converter = new LinkedList<>(urlPrivateImgs);

			converter = removeDuplicateElemet(converter);

			tutor.setPrivateImgs(Sets.newHashSet(converter));

			iTutorRepository.save(tutor);

			return "Insert private img successfully";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<String> findAllPrivateImgs() {
		List<String> listObject = new LinkedList<>();

		ObjectListing iterables = client.listObjects(bucketnamePrivateimgs);
		for (S3ObjectSummary os : iterables.getObjectSummaries()) {
			listObject.add(tutorPrivateimgsURL + os.getKey());
		}

		return listObject;
	}

//public Imgs

	@Override
	public String uploadImageToAmazonPubclicImgs(MultipartFile multipartFile, String filename) {
		try {

			String url = uploadMultipartFile(multipartFile, filename, bucketnamePublicimgs, tutorPublicimgsURL);

			System.out.println(url);

			Tutor tutor = iTutorRepository.getOne(
					Long.parseLong(filename.substring(filename.lastIndexOf("/") + 1, filename.lastIndexOf("Public"))));

			Set<String> urlPublicImgs = tutor.getPublicImgs();

			urlPublicImgs.add(url);

			List<String> converter = new LinkedList<>(urlPublicImgs);

			converter = removeDuplicateElemet(converter);

			tutor.setPublicImgs(Sets.newHashSet(converter));

			iTutorRepository.save(tutor);

			return "Insert public Imgs successfully";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<String> findAllPublicImgs() {
		List<String> listObject = new LinkedList<>();

		ObjectListing iterables = client.listObjects(bucketnamePublicimgs);
		for (S3ObjectSummary os : iterables.getObjectSummaries()) {
			listObject.add(tutorPublicimgsURL + os.getKey());
		}

		return listObject;
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

			Tutor tutor = iTutorRepository.getOne(
					Long.parseLong(urlFile.substring(urlFile.lastIndexOf("/") + 1, urlFile.lastIndexOf("Private"))));

			Set<String> privateImgs = tutor.getPrivateImgs();

			privateImgs.remove(urlFile);

			tutor.setPrivateImgs(privateImgs);

			iTutorRepository.save(tutor);

		} catch (AmazonServiceException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void deleteByFileNameAndIDPublicImgs(String urlFile) {
		try {

			client.deleteObject(bucketnamePublicimgs, urlFile.substring(urlFile.lastIndexOf('/') + 1));

			Tutor tutor = iTutorRepository.getOne(
					Long.parseLong(urlFile.substring(urlFile.lastIndexOf("/") + 1, urlFile.lastIndexOf("Public"))));

			Set<String> publicImgs = tutor.getPublicImgs();

			publicImgs.remove(urlFile);

			tutor.setPublicImgs(publicImgs);

			iTutorRepository.save(tutor);

		} catch (AmazonServiceException e) {

			e.printStackTrace();
		}
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

			String url = uploadMultipartFile(multipartFile, filename, bucketnamePrivateimgs, tutorPrivateimgsURL);

			return " Update private img successfully";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String updatePublicImageToAmazon(MultipartFile multipartFile, String filename) {
		try {

			String url = uploadMultipartFile(multipartFile, filename, bucketnamePublicimgs, tutorPublicimgsURL);

			return " Update public img successfully";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
