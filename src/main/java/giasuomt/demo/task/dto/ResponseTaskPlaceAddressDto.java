package giasuomt.demo.task.dto;

import giasuomt.demo.location.model.Area;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseTaskPlaceAddressDto {
	
	private String relAddNumber;
	
	private String relAddStreet;
	
	private Area area;
	
	private String relAddNote;
}
