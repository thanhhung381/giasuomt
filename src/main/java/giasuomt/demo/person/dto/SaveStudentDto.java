package giasuomt.demo.person.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveStudentDto {
	
	private Long id;
	
	private String confirmImgs;

	private String nowLevel;

	private LocalDateTime nowLevelUpdatedAt;

	private String institutionName;

	private String institutionAbbrName;

	private String institutionCode;

	private String institutionType;

	private String majorName;

	private String majorCode;

}
