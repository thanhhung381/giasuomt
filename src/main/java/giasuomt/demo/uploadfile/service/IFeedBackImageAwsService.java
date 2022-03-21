package giasuomt.demo.uploadfile.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import giasuomt.demo.uploadfile.model.FeedbackImageAws;
import giasuomt.demo.uploadfile.model.RetainedImgsIdentificationAws;

public interface IFeedBackImageAwsService {
	
   FeedbackImageAws uploadImageToAmazon(MultipartFile multipartFile);
	
	public List<FeedbackImageAws> findAll();
	
	public void deleteByFileNameAndID(String urlFile,Long id);
	
	boolean checkExistIdOfT(Long id);
	
	boolean checkExistObjectinS3(String name);
	
	public void deleteById(Long id);
	
}
