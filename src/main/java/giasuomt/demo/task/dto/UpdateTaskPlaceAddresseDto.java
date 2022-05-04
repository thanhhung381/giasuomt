package giasuomt.demo.task.dto;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import giasuomt.demo.location.model.SaveTaskPlaceAddressDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTaskPlaceAddresseDto {

	private String id;
	
	private Set<SaveTaskPlaceAddressDto> placeAddressDtos=new HashSet<>();
	
	
}
