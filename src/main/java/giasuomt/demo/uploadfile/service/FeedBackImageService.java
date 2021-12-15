package giasuomt.demo.uploadfile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import giasuomt.demo.uploadfile.model.BillImage;
import giasuomt.demo.uploadfile.model.FeedBackImage;
import giasuomt.demo.uploadfile.model.ResponsiveBillImage;
import giasuomt.demo.uploadfile.model.ResponsiveFeedBackImage;
import giasuomt.demo.uploadfile.repository.IFeedBackImageRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FeedBackImageService implements IFeedBackImageService {

	
	private IFeedBackImageRepository iFeedbackImageRepository;
	
	
	
	public FeedBackImage save(MultipartFile file)
	{
		try {
			
			FeedBackImage feedBackImage=new FeedBackImage();
			
			feedBackImage.setContentType(file.getContentType());
			
			feedBackImage.setData(file.getBytes());
			
			feedBackImage.setSize(file.getSize());
			
			feedBackImage.setNameFile(StringUtils.cleanPath(file.getOriginalFilename()));
			
			
			return iFeedbackImageRepository.save(feedBackImage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void map(ResponsiveFeedBackImage responsiveFeedBackImage,FeedBackImage feedBackImage)
	{
		String download=ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/feedBackImage/downloadFile/")
				.path(feedBackImage.getNameFile()).toUriString();
		
		responsiveFeedBackImage.setId(feedBackImage.getId());
		
		responsiveFeedBackImage.setContentType(feedBackImage.getContentType());
		responsiveFeedBackImage.setName(feedBackImage.getNameFile());
		responsiveFeedBackImage.setSize(feedBackImage.getSize());
		responsiveFeedBackImage.setUrl(download);
	}
	
	private List<ResponsiveFeedBackImage> maptoList(List<FeedBackImage> entities) {
		List<ResponsiveFeedBackImage> files = new ArrayList<>();
		for (FeedBackImage entity : entities) {
			ResponsiveFeedBackImage file = new ResponsiveFeedBackImage();
			map(file, entity);
			files.add(file);
		}
		return files;
	}
	
	
	public List<ResponsiveFeedBackImage> findAll() {
		List<FeedBackImage> entities =iFeedbackImageRepository.findAll();
		List<ResponsiveFeedBackImage> files = maptoList(entities);
		return files;
	}
	
	public FeedBackImage getByNameFile(String nameFile)
	{
		return iFeedbackImageRepository.findByNameFile(nameFile);
	}
	
}
