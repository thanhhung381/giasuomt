package giasuomt.demo.uploadfile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import giasuomt.demo.uploadfile.model.Avatar;
import giasuomt.demo.uploadfile.model.BillImage;
import giasuomt.demo.uploadfile.model.ResponsiveAvatar;
import giasuomt.demo.uploadfile.model.ResponsiveBillImage;
import giasuomt.demo.uploadfile.repository.IBillImageRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BillImageService implements IBillImageService {

	private IBillImageRepository iBillImageRepository;

	public BillImage save(MultipartFile multipartFile) {
		try {
			
			BillImage billImage=new BillImage();
			
			billImage.setContentType(multipartFile.getContentType());
			
			billImage.setData(multipartFile.getBytes());
			
			billImage.setSize(multipartFile.getSize());
			
			billImage.setNameFile(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
			
			
			return iBillImageRepository.save(billImage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void map(BillImage billImage,ResponsiveBillImage responsiveBillImage)
	{
		String download = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/billImage/downloadFile/")
				.path(billImage.getNameFile()).toUriString();
		
		responsiveBillImage.setId(billImage.getId());
	
		
	   responsiveBillImage.setContentType(billImage.getContentType());
	   responsiveBillImage.setName(billImage.getNameFile());
	   responsiveBillImage.setSize(billImage.getSize());
		responsiveBillImage.setUrl(download);

		
	}
	
	private List<ResponsiveBillImage> maptoList(List<BillImage> entities) {
		List<ResponsiveBillImage> files = new ArrayList<>();
		for (BillImage entity : entities) {
			ResponsiveBillImage file = new ResponsiveBillImage();
			map(entity, file);
			files.add(file);
		}
		return files;
	}
	
	
	public List<ResponsiveBillImage> findAll() {
		List<BillImage> entities =iBillImageRepository.findAll();
		List<ResponsiveBillImage> files = maptoList(entities);
		return files;
	}
	
	
	public BillImage getByNameFile(String nameFile)
	{
		return iBillImageRepository.findByNameFile(nameFile);
	}
	

}
