package giasuomt.demo.uploadfile.service;

import java.io.IOException;


import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import giasuomt.demo.uploadfile.model.RetainedImgsIdentificationAws;

public interface IRetainedImgsIdentificationAwsService {

	RetainedImgsIdentificationAws uploadImageToAmazon(MultipartFile multipartFile);
	
	public List<RetainedImgsIdentificationAws> findAll();
	
	public void deleteByFileNameAndID(String urlFile,Long id);
	
	boolean checkExistIdOfT(Long id);
	
	boolean checkExistObjectinS3(String name);
	
	public void deleteById(Long id);
	 
}
