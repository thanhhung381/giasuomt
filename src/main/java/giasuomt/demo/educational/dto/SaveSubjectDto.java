package giasuomt.demo.educational.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveSubjectDto {
	
	private Long id;
	
	private String name;
	
	private String type;
	
	private String subject;
	
	private String level;
	
	private Long  idSubjectGroup;
}
