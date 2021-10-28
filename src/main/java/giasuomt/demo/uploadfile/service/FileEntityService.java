package giasuomt.demo.uploadfile.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.uploadfile.model.FileEntity;
import giasuomt.demo.uploadfile.model.ResponsiveFile;
import giasuomt.demo.uploadfile.repository.IFileEntityRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FileEntityService implements IFIleEntityService {

	private IFileEntityRepository iFileEntityRepository;

	public FileEntity save(MultipartFile multipartFile) {
		try {

			FileEntity fileEntity = new FileEntity();

			fileEntity.setContentType(multipartFile.getContentType());

			fileEntity.setData(multipartFile.getBytes());

			fileEntity.setSize(multipartFile.getSize());

			fileEntity.setNameFile(StringUtils.cleanPath(multipartFile.getOriginalFilename()));

			return iFileEntityRepository.save(fileEntity);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	public void map(FileEntity entity, ResponsiveFile responsiveFile) {
		String download = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/file/downloadFile/")
				.path(entity.getNameFile()).toUriString();

		responsiveFile.setId(entity.getId());
		responsiveFile.setContentType(entity.getContentType());
		responsiveFile.setName(entity.getNameFile());
		responsiveFile.setSize(entity.getSize());
		responsiveFile.setUrl(download);

	}

	private List<ResponsiveFile> maptoList(List<FileEntity> entities) {
		List<ResponsiveFile> files = new ArrayList<>();
		for (FileEntity entity : entities) {
			ResponsiveFile file = new ResponsiveFile();
			map(entity, file);
			files.add(file);
		}
		return files;
	}

	public List<ResponsiveFile> findAll() {
		List<FileEntity> entities = iFileEntityRepository.findAll();
		List<ResponsiveFile> files = maptoList(entities);
		return files;
	}

	
	
	@Override
	public FileEntity getByNameFile(String nameFile) {

		return iFileEntityRepository.findByNameFile(nameFile);
	}

}
