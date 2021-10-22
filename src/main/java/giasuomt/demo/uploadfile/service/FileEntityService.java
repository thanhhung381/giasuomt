package giasuomt.demo.uploadfile.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import giasuomt.demo.uploadfile.model.FileEntity;
import giasuomt.demo.uploadfile.model.ResponsiveFile;
import giasuomt.demo.uploadfile.repository.IFileEntityRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FileEntityService {

		private IFileEntityRepository iFileEntityRepository;
		
		
		public FileEntity save(MultipartFile multipartFile) throws IOException
		{
			FileEntity fileEntity=new FileEntity();
			fileEntity.setNameFile(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
			fileEntity.setContentType(multipartFile.getContentType());
			fileEntity.setData(multipartFile.getBytes());
			fileEntity.setSize(multipartFile.getSize());
			
			return iFileEntityRepository.save(fileEntity);
			
			
		}
	
		
		
	
		private void map(FileEntity entity,ResponsiveFile responsiveFile)
		{
			String download=ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(String.valueOf((long)entity.getId())).toUriString();
			
			
			responsiveFile.setId(entity.getId());
			responsiveFile.setContentType(entity.getContentType());
			responsiveFile.setName(entity.getNameFile());
			responsiveFile.setSize(entity.getSize());
			responsiveFile.setUrl(download);
			
		}
		
		
		private List<ResponsiveFile> maptoList(List<FileEntity> entities)
		{
			List<ResponsiveFile> files=new ArrayList<>();
			for (FileEntity entity : entities) {
				ResponsiveFile file=new ResponsiveFile();
				map(entity, file);
				files.add(file);
			}
			return files;
		}
		
		public List<ResponsiveFile> findAll()
		{
			List<FileEntity> entities=iFileEntityRepository.findAll();
			List<ResponsiveFile> files=maptoList(entities);
			return files;
		}
		
}
