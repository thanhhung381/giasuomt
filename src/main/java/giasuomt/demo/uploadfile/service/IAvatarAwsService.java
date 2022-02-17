package giasuomt.demo.uploadfile.service;

import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import giasuomt.demo.uploadfile.model.AvatarAws;



public interface IAvatarAwsService {

	AvatarAws uploadImageToAmazon(MultipartFile multipartFile);
	
	public List<AvatarAws> findAll();
	
	public void deleteByFileNameAndID(String urlFile,Long id);

	boolean checkExistIdOfT(Long id);
	
	boolean checkExistObjectinS3(String name);
}
