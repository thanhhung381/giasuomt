package giasuomt.demo.commondata.dto;
import java.time.LocalDate;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import giasuomt.demo.commondata.util.DateUtils;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
public class SaveUserDTO_Staff {
//	@NotBlank(message = "{role.name.notblank}")
//	@Length(min = 4, max = 50, message = "{role.description.size}")
	private String fullName;
	private String gender;
	private String phones;
	private String zaloes;
	private String emails;
	private String fbs;
	@JsonDeserialize //Để deserialize từ dạng string của JSON về dạng LocalDate 
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT) //Quy định date format khi nó add đối tượng thành Json để trả về Clients
	@DateTimeFormat(pattern = DateUtils.DATE_FORMAT) //Quy định date format để lưu xuống database
	private LocalDate birthDate;
	private String birthYear;	
//	@NotBlank(message = "{role.description.notblank}")
//	private String description;

}
