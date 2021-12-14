package giasuomt.demo.uploadfile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import giasuomt.demo.uploadfile.model.BillImage;
import giasuomt.demo.uploadfile.model.ResponsiveBillImage;

public interface IBillImageService {
	
	
	
	public BillImage save(MultipartFile multipartFile);

	public void map(BillImage billImage,ResponsiveBillImage responsiveBillImage);

	
	public List<ResponsiveBillImage> findAll(); 
	
	
	public BillImage getByNameFile(String nameFile);

	
}
