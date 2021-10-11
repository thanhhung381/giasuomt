package giasuomt.demo.person.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveGraduatedStudentDto {

	private Long id;
	
	private String diplomaImgs;

	private String graduatedYear;

	private String institutionName;

	private String institutionAbbrName;

	private String institutionCode;

	private String institutionType;

	private String majorName;

	private String majorCode;

}
