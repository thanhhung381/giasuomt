package giasuomt.demo.uploadfile.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import giasuomt.demo.uploadfile.model.Avatar;
import giasuomt.demo.uploadfile.model.ResponsiveAvatar;

public interface IAvatarService {

	public Avatar save(MultipartFile multipartFile);

	public Avatar getByNameFile(String nameFile);

	void map(Avatar entity, ResponsiveAvatar responsiveFile);

	public List<ResponsiveAvatar> findAll();
}
