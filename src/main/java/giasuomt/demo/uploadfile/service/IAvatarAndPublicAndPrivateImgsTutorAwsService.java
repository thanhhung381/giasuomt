package giasuomt.demo.uploadfile.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IAvatarAndPublicAndPrivateImgsTutorAwsService {
	String uploadImageToAmazon(MultipartFile multipartFile,String filename);
	
	public List<String> findAll();
	
	public List<String> findAllPrivateImgs();
	
	public List<String> findAllPublicImgs();
	
	public void deleteByFileNameAndID(String urlFile);

	public String uploadImageToAmazonPrivateImgs(MultipartFile multipartFile, String filename);
	
	public String uploadImageToAmazonPubclicImgs(MultipartFile multipartFile, String filename);
	
	boolean checkExistObjectinS3(String name);
	
	boolean checkExistObjectPrivateInS3(String name);
	
	boolean checkExistObjectPublicInS3(String name);
	
	public String findAllByNameContainer(String name,List<String> containURLs);
	
	public void deleteByFileNameAndIDPrivateImgs(String urlFile);
	
	public void deleteByFileNameAndIDPublicImgs(String urlFile);
	
String updatePrivateImageToAmazon(MultipartFile multipartFile,String filename);
	
	String updatePublicImageToAmazon(MultipartFile multipartFile,String filename);
	

}
