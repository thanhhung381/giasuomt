package giasuomt.demo.uploadfile.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import giasuomt.demo.uploadfile.model.FileEntity;
import giasuomt.demo.uploadfile.model.ResponsiveFile;

public interface IFIleEntityService {

	public FileEntity save(MultipartFile multipartFile);

	public FileEntity getByNameFile(String nameFile);

	void map(FileEntity entity, ResponsiveFile responsiveFile);

	public List<ResponsiveFile> findAll();
}
