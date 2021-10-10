package giasuomt.demo.tutor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveInstitutionTeacherDto {
	
	private Long id;
	
	private String confirmImgs;

	private String institutionType;

	private String institutionName;

	private String institutionAbbrName;

	private String subject;

}
