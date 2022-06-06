package giasuomt.demo.uploadfile.service;

import java.util.List;



import org.springframework.web.multipart.MultipartFile;





public interface IAvatarAwsService {

	public String uploadImageToAmazon(MultipartFile multipartFile,String id);
	
	public List<String> findAll();
	
	public void deleteByFileNameAndID(String urlFile);

	
	
	boolean checkExistObjectinS3(String name);
	
	
}
