package giasuomt.demo.tutor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveSchoolTeacherDto  {
	
	private Long schoolTeacherId;
	
	private Long tutorId;

	private String confirmImgs;

	private String subject;

	String institutionName;

	String institutionAbbrName;

	String institutionCode;

	String institutionType;
}
