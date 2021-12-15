package giasuomt.demo.uploadfile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import giasuomt.demo.uploadfile.model.FeedBackImage;
import giasuomt.demo.uploadfile.model.ResponsiveFeedBackImage;

public interface IFeedBackImageService {
	
	public FeedBackImage getByNameFile(String nameFile);

	public FeedBackImage save(MultipartFile file);

	
	public void map(ResponsiveFeedBackImage responsiveFeedBackImage,FeedBackImage feedBackImage);

	
	
	public List<ResponsiveFeedBackImage> findAll();
	
}
