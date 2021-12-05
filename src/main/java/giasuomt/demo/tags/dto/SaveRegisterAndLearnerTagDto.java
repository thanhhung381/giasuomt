package giasuomt.demo.tags.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveRegisterAndLearnerTagDto {
	private Long id;
	
	private String tagType;
	
	private String tagGroup;
	
	private String tagName;
	
	private String description;
}
