package giasuomt.demo.location.dto;

<<<<<<< Updated upstream
import javax.validation.constraints.NotBlank;

import giasuomt.demo.location.Validation.CheckDuplicateProvincialLevelAnđistrictAndCommune;
import giasuomt.demo.location.Validation.CheckIfNationAndStateCanBeNULL;
import giasuomt.demo.location.Validation.CheckIfNationWithBasicInfo;
=======
import giasuomt.demo.location.Validation.CheckDuplicateArea;

import giasuomt.demo.location.Validation.CheckIfNationIsVietnam;

>>>>>>> Stashed changes
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
<<<<<<< Updated upstream
@CheckIfNationAndStateCanBeNULL
@CheckIfNationWithBasicInfo
@CheckDuplicateProvincialLevelAnđistrictAndCommune
=======
@CheckIfNationIsVietnam //Nếu nation là Việt Nam thì provincialLevel, district, commune ko được null hoặc blank hoặc Empty.
@CheckDuplicateArea
>>>>>>> Stashed changes
public class CreateAreaDTO {
	
	protected String nation;
	
	protected String state;
	
	protected String provincialLevel;
	
	protected String district;
	
	protected String commune;
	
	protected String xRelCoo;	
	
	protected String yRelCoo;
}
