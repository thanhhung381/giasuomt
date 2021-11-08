package giasuomt.demo.location.dto;

import org.springframework.web.multipart.MultipartFile;

import giasuomt.demo.person.dto.SavePersonDto;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Payload {
	private SavePersonDto dto;
	
	private MultipartFile file;
}
