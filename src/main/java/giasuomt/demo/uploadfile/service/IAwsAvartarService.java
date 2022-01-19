package giasuomt.demo.uploadfile.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import giasuomt.demo.uploadfile.model.AwsAvatar;

public interface IAwsAvartarService {
	AwsAvatar uploadImageToAmazon(MultipartFile multipartFile);
	
	public List<AwsAvatar> findAll();
	
	public List<AwsAvatar> uploadFiles(List<MultipartFile> multipartFile);
	
}
