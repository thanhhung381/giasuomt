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
import giasuomt.demo.uploadfile.model.Avatar;
import giasuomt.demo.uploadfile.model.ResponsiveAvatar;
import giasuomt.demo.uploadfile.repository.IAvatarRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AvatarService implements IAvatarService {

	private IAvatarRepository iFileEntityRepository;

	public Avatar save(MultipartFile multipartFile) {
		try {

			Avatar fileEntity = new Avatar();

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

	public void map(Avatar entity, ResponsiveAvatar responsiveFile) {
		String download = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/file/downloadFile/")
				.path(entity.getNameFile()).toUriString();

		responsiveFile.setId(entity.getId());
		responsiveFile.setContentType(entity.getContentType());
		responsiveFile.setName(entity.getNameFile());
		responsiveFile.setSize(entity.getSize());
		responsiveFile.setUrl(download);

	}

	private List<ResponsiveAvatar> maptoList(List<Avatar> entities) {
		List<ResponsiveAvatar> files = new ArrayList<>();
		for (Avatar entity : entities) {
			ResponsiveAvatar file = new ResponsiveAvatar();
			map(entity, file);
			files.add(file);
		}
		return files;
	}

	public List<ResponsiveAvatar> findAll() {
		List<Avatar> entities = iFileEntityRepository.findAll();
		List<ResponsiveAvatar> files = maptoList(entities);
		return files;
	}

	
	
	@Override
	public Avatar getByNameFile(String nameFile) {

		return iFileEntityRepository.findByNameFile(nameFile);
	}

}
