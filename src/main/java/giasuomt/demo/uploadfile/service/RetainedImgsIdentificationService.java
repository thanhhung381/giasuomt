package giasuomt.demo.uploadfile.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.internal.asm.tree.MultiANewArrayInsnNode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import giasuomt.demo.uploadfile.model.Avatar;
import giasuomt.demo.uploadfile.model.ResponsiveAvatar;
import giasuomt.demo.uploadfile.model.ResponsiveRetainedImgsIdentification;
import giasuomt.demo.uploadfile.model.RetainedImgsIdentification;
import giasuomt.demo.uploadfile.repository.IRetainedImgsIdentificationRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RetainedImgsIdentificationService implements IRetainedImgsIdentificationService {

	private IRetainedImgsIdentificationRepository iRetainedImgsIdentificationRepository;

	public void mapDto(ResponsiveRetainedImgsIdentification responsiveRetainedImgsIdentification,
			RetainedImgsIdentification retainedImgsIdentification) {
		String urlDownload = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/RetainedImgsIdentification/downloadFile/").path(retainedImgsIdentification.getNameFile())
				.toUriString();

		responsiveRetainedImgsIdentification.setId(retainedImgsIdentification.getId());

		responsiveRetainedImgsIdentification.setNameFile(retainedImgsIdentification.getNameFile());

		responsiveRetainedImgsIdentification.setSize(retainedImgsIdentification.getSize());

		responsiveRetainedImgsIdentification.setContentType(retainedImgsIdentification.getContentType());

		responsiveRetainedImgsIdentification.setUrl(urlDownload);

	}

	public RetainedImgsIdentification save(MultipartFile file)  {

		try {
			RetainedImgsIdentification retainedImgsIdentification = new RetainedImgsIdentification();

			retainedImgsIdentification.setContentType(file.getContentType());

			retainedImgsIdentification.setNameFile(StringUtils.cleanPath(file.getOriginalFilename()));

			retainedImgsIdentification.setSize(file.getSize());

			retainedImgsIdentification.setData(file.getBytes());

			return iRetainedImgsIdentificationRepository.save(retainedImgsIdentification);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		return null;
	}

	private List<ResponsiveRetainedImgsIdentification> maptoList(List<RetainedImgsIdentification> entities) {
		List<ResponsiveRetainedImgsIdentification> files = new ArrayList<>();
		for (RetainedImgsIdentification entity : entities) {
			ResponsiveRetainedImgsIdentification file = new ResponsiveRetainedImgsIdentification();
			mapDto(file, entity);
			files.add(file);
		}
		return files;
	}

	public List<ResponsiveRetainedImgsIdentification> findAll() {
		List<RetainedImgsIdentification> entities = iRetainedImgsIdentificationRepository.findAll();
		List<ResponsiveRetainedImgsIdentification> files = maptoList(entities);
		return files;
	}

	@Override
	public RetainedImgsIdentification getByNameFile(String nameFile) {

		return iRetainedImgsIdentificationRepository.findByNameFile(nameFile);
	}

}
