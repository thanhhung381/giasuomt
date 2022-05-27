package giasuomt.demo.uploadfile.service;

import java.io.IOException;



import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



public interface IRetainedImgsIdentificationAwsService {

	  String uploadImageToAmazon(MultipartFile multipartFile,String fileName);
		
		public List<String> findAll();
		
		public void deleteByFileNameAndID(String urlFile);
		
		boolean checkExistIdOfT(Long id);
		
		boolean checkExistObjectinS3(String name);
		
		public String findAllByNameContainer(String name, List<String> containURLs);

		String updateRetainedImgsIdentificationToAmazon(MultipartFile file, String nameFile);
	 
}
