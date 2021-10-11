package giasuomt.demo.location.dto;

import giasuomt.demo.location.Validation.CheckDuplicateArea;


import giasuomt.demo.location.Validation.CheckIfNationIsVietnam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CheckIfNationIsVietnam //Nếu nation là Việt Nam thì provincialLevel, district, commune ko được null hoặc blank hoặc Empty.
@CheckDuplicateArea
public class SaveAreaDto {
	protected Long id;
	
	protected String nation;
	
	protected String state;
	
	protected String provincialLevel;
	
	protected String district;
	
	protected String commune;
	
	protected String xRelCoo;	
	
	protected String yRelCoo;
}
