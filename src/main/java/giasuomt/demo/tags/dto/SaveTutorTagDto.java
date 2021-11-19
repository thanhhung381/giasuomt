package giasuomt.demo.tags.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveTutorTagDto {
	private Long id;
	
	private String tagType;
	
	private String tagGroup;
	
	private String tagName;
	
	private String description;
}
