package giasuomt.demo.uploadfile.service;

import java.util.ArrayList;



import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


public interface IFeedBackImageAwsService {
	
	String uploadImageToAmazon(MultipartFile multipartFile, String filename);

	

	public List<String> findAllPrivateImgs();

	public List<String> findAllPublicImgs();

	

	public String uploadImageToAmazonPrivateImgs(MultipartFile multipartFile, String filename);

	public String uploadImageToAmazonPubclicImgs(MultipartFile multipartFile, String filename);


	boolean checkExistObjectPrivateInS3(String name);

	boolean checkExistObjectPublicInS3(String name);

	public String findAllByNameContainer(String name, List<String> containURLs);

	public void deleteByFileNameAndIDPrivateImgs(String urlFile);

	public void deleteByFileNameAndIDPublicImgs(String urlFile);

	String updatePrivateImageToAmazon(MultipartFile multipartFile, String filename);

	String updatePublicImageToAmazon(MultipartFile multipartFile, String filename);

	
}
