package giasuomt.demo.uploadfile.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import giasuomt.demo.uploadfile.model.ResponsiveRetainedImgsIdentification;
import giasuomt.demo.uploadfile.model.RetainedImgsIdentification;

public interface IRetainedImgsIdentificationService {

	public void mapDto(ResponsiveRetainedImgsIdentification responsiveRetainedImgsIdentification,
			RetainedImgsIdentification retainedImgsIdentification);

	public RetainedImgsIdentification save(MultipartFile file) ;

	public List<ResponsiveRetainedImgsIdentification> findAll();

	public RetainedImgsIdentification getByNameFile(String nameFile);
}
