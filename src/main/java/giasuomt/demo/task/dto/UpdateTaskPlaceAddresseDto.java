package giasuomt.demo.task.dto;

import java.util.LinkedList;
import java.util.List;

import giasuomt.demo.location.model.SaveTaskPlaceAddressDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTaskPlaceAddresseDto {

	private String id;
	
	private List<SaveTaskPlaceAddressDto> placeAddressDtos=new LinkedList<>();
	
	
}
