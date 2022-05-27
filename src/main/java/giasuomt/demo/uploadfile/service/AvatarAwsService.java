package giasuomt.demo.uploadfile.service;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;


import giasuomt.demo.uploadfile.ultils.AwsClientS3;
import giasuomt.demo.uploadfile.ultils.FileUltils;
import giasuomt.demo.user.model.User;
import giasuomt.demo.user.repository.IUserRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
public class AvatarAwsService extends AwsClientS3 implements IAvatarAwsService {

	

	@Autowired
	private IUserRepository iUserRepository;

	@Value("${amazon.avatarURL}")
	private String urlAvatar;

	@Value("${amazon.bucketnameAvatar}")
	private String bucketName;

	private void upploadPublicFile(String filename, File file) {
		client.putObject(
				new PutObjectRequest(bucketName, filename, file).withCannedAcl(CannedAccessControlList.PublicRead));
	}

	private String uploadMultipartFile(MultipartFile multipartFile, String nameFile) {
		String imageURL = null;

		try {

			File file = FileUltils.convertMultiPathToFile(multipartFile);

			upploadPublicFile(nameFile, file);

			file.delete();

			//urlAvatar "http://meomeo/"
			imageURL = urlAvatar.concat(nameFile);

			return imageURL;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String uploadImageToAmazon(MultipartFile multipartFile, String username) {

		try {

			String url = uploadMultipartFile(multipartFile, username + "Avatar");

			User user = iUserRepository.findByUsername(username).get();

			user.setAvatar(url);
 
			iUserRepository.save(user);

			return "Insert Or Update Avatar successfully";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void deleteByFileNameAndID(String urlFile) {
		try {

			client.deleteObject(bucketName, urlFile.substring(urlFile.lastIndexOf('/') + 1));
			
			User user = iUserRepository.findByUsername(urlFile.substring(urlFile.lastIndexOf('/') + 1)).get();

			user.setAvatar(null);
 
			iUserRepository.save(user);

		} catch (AmazonServiceException e) {

			e.printStackTrace();
		}
	}

//	
	@Override
	public List<String> findAll() {

		List<String> listObject = new LinkedList<>();

		ObjectListing iterables = client.listObjects(bucketName);
		for (S3ObjectSummary os : iterables.getObjectSummaries()) {
			listObject.add(urlAvatar+os.getKey());
		}

		return listObject;
	}



	@Override
	public boolean checkExistObjectinS3(String name) {

		try {
			boolean flag = this.client.doesObjectExist(bucketName, name);
			if (flag)
				return true;

		} catch (SdkClientException e) {

			e.printStackTrace();
		}
		return false;
	}

}
