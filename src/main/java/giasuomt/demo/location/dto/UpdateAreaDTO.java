package giasuomt.demo.location.dto;

import giasuomt.demo.location.Validation.CheckDuplicateProvincialLevelAnđistrictAndCommune;


import giasuomt.demo.location.Validation.ExistIdArea;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAreaDTO {
	
	@ExistIdArea
	protected Long idArea;
	
	protected String nation;
	
	protected String state;
	
	protected String provincialLevel;
	
	protected String district;
	
	protected String commune;
	
	protected String xRelCoo;	
	
	protected String yRelCoo;

}
