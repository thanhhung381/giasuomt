package giasuomt.demo.location.dto;

import javax.validation.constraints.NotBlank;


import giasuomt.demo.location.Validation.CheckDuplicateProvincialLevelAnđistrictAndCommune;

import giasuomt.demo.location.Validation.CheckIfNationIsVietnam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CheckIfNationIsVietnam
public class CreateAreaDTO {
	
	protected String nation;
	
	protected String state;
	
	protected String provincialLevel;
	
	protected String district;
	
	protected String commune;
	
	protected String xRelCoo;	
	
	protected String yRelCoo;

	

}
