package giasuomt.demo.person.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveRelationshipDto {
	
	private Long id;
	
	//private Long idPersonWith;//A
	
	private Long idPersonBy;//B tồn tại trong DB
	
	private String relationshipType;
	
}
