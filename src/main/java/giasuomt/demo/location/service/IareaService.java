package giasuomt.demo.location.service;


import java.util.List;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.location.dto.FindingDtoArea;
import giasuomt.demo.location.model.Area;

public interface IareaService extends IGenericService<Area, Long> {
	boolean checkExistIdofArea(Long id);
	List<Area> findByNationAndProvincialLevelAndDistrictAndCommune(FindingDtoArea dtoArea);
}
