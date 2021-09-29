package giasuomt.demo.location.service;


import java.util.List;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.location.dto.FindingDtoArea;
import giasuomt.demo.location.model.Area;

public interface IAreaService extends IGenericService<Area, Long> {
	boolean checkExistIdofArea(Long id);
	
	boolean checkExistProvincialLevel(String provincialLevel);
	boolean checkExistDistrict(String district);
	boolean checkExistCommune(String commune);
	
	
	
	List<Area> findByNationAndProvincialLevelAndDistrictAndCommune(FindingDtoArea dtoArea);
}
