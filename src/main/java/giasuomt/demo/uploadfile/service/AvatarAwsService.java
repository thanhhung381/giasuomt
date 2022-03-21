package giasuomt.demo.uploadfile.service;

import java.io.File;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

import giasuomt.demo.uploadfile.model.AvatarAws;
import giasuomt.demo.uploadfile.repository.IAvatarAwsRepository;
import giasuomt.demo.uploadfile.ultils.AwsClientS3;
import giasuomt.demo.uploadfile.ultils.FileUltils;
import lombok.AllArgsConstructor;

@Service
public class AvatarAwsService extends AwsClientS3  implements IAvatarAwsService {
	
	@Autowired
	private IAvatarAwsRepository iAvatarAwsRepository;
	
	@Value("${amazon.avatarURL}")
	private String urlAvatar;
	
	@Value("${amazon.bucketnameAvatar}")
	private String bucketName;
	
	private void upploadPublicFile(String filename,File file)
	{
		amazonS3.putObject(new PutObjectRequest(bucketName, filename, file).withCannedAcl(CannedAccessControlList.PublicRead));
	}
	
	private String uploadMultipartFile(MultipartFile multipartFile)
	{
		String imageURL=null;
		
		try {
			
			File file=FileUltils.convertMultiPathToFile(multipartFile);
			
			String nameFile=FileUltils.generateNameFile(multipartFile);
			
			upploadPublicFile(nameFile, file);
			
			file.delete();
			
			imageURL=urlAvatar.concat(nameFile);
			
			return imageURL;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public AvatarAws uploadImageToAmazon(MultipartFile multipartFile) {
		
		
		AvatarAws avatarAws=new AvatarAws();
		try {
			
			String url=uploadMultipartFile(multipartFile);
			
			avatarAws.setUrlAvatar(url);
			
			return iAvatarAwsRepository.save(avatarAws);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public void deleteByFileNameAndID(String urlFile,Long id)
	{
		try {
			
	
			
			amazonS3.deleteObject(bucketName,urlFile.substring(urlFile.lastIndexOf('/')+1));
			iAvatarAwsRepository.deleteById(id);
			
		} catch (AmazonServiceException e) {
			
			e.printStackTrace();
		} catch (SdkClientException e) {
			
			e.printStackTrace();
		}
	}
//	
	@Override
	public List<AvatarAws> findAll() {
		
		return iAvatarAwsRepository.findAll();
	}

	@Override
	public boolean checkExistIdOfT(Long id) {
		
		return iAvatarAwsRepository.countById(id)>=1;
	}

	@Override
	public boolean checkExistObjectinS3(String name) {
		if(amazonS3.doesObjectExist(bucketName, name))
			return true;
		return false;
	}

	@Override
	public void deleteById(Long id) {
		iAvatarAwsRepository.deleteById(id);
		
	}

}
