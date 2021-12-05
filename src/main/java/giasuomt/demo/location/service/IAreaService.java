package giasuomt.demo.location.service;
import java.util.List;
import javax.validation.Valid;
import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.location.dto.FindingVietnamAreaDto;
import giasuomt.demo.location.dto.SaveAreaDto;
import giasuomt.demo.location.model.Area;

public interface IAreaService extends IGenericService<SaveAreaDto, Area, String> {

	boolean checkExistIdofArea(Long id);
	
	boolean checkExistProvincialLevel(String provincialLevel);
	boolean checkExistDistrict(String district);
	boolean checkExistCommune(String commune);
		
	List<Area> findByNationAndProvincialLevelAndDistrictAndCommune(FindingVietnamAreaDto dtoArea);

	Area create(@Valid SaveAreaDto dto);

	Area update(SaveAreaDto dto);

	Area save(SaveAreaDto dto, Area area);

	

	
	
}
