package giasuomt.demo.tutor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveSchoolTeacherDto  {
	
	private Long id;

	private String confirmImgs;

	private String subject;

	private String institutionName;

	private String institutionAbbrName;

	private String institutionCode;

	private String institutionType;
	
	private Long tutorId;
}
