package giasuomt.demo.location.dto;
import org.springframework.web.multipart.MultipartFile;
import giasuomt.demo.person.dto.SaveTutorDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payload {
	private SaveTutorDto dto;
	private MultipartFile file;
}
