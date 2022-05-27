package giasuomt.demo.uploadfile.service;

import java.util.ArrayList;



import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



public interface IBillImageAwsService {
	
	
	
	public String uploadImageToAmazon(MultipartFile multipartFile,Long id);
	
	public List<String> findAll();
	
	public void deleteByFileNameAndID(String urlFile);
	

	
	boolean checkExistObjectinS3(String name);


	
}
