package giasuomt.demo.location.service;


import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.learnerAndRegister.model.LearnerAndRegister;
import giasuomt.demo.location.dto.CreateAreaDTO;
import giasuomt.demo.location.dto.FindingDtoArea;
import giasuomt.demo.location.dto.UpdateAreaDTO;
import giasuomt.demo.location.model.Area;

public interface IAreaService extends IGenericService<Area, Long> {
	boolean checkExistIdofArea(Long id);
	
	boolean checkExistProvincialLevel(String provincialLevel);
	boolean checkExistDistrict(String district);
	boolean checkExistCommune(String commune);
	
	
	
	List<Area> findByNationAndProvincialLevelAndDistrictAndCommune(FindingDtoArea dtoArea);

	Area save(@Valid CreateAreaDTO dto);

	Area update(@Valid UpdateAreaDTO updateDto);

	Set<LearnerAndRegister> findLearnerAndRegistersById(Long areaId);
}
