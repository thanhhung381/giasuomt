package giasuomt.demo.location.service;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.location.dto.FindingDtoArea;
import giasuomt.demo.location.dto.SaveAreaDto;
import giasuomt.demo.location.model.Area;

public interface IAreaService  {

	boolean checkExistIdofArea(Long id);
	
	boolean checkExistProvincialLevel(String provincialLevel);
	boolean checkExistDistrict(String district);
	boolean checkExistCommune(String commune);
		
	List<Area> findByNationAndProvincialLevelAndDistrictAndCommune(FindingDtoArea dtoArea);

	Area create(@Valid SaveAreaDto dto);

	Area update(SaveAreaDto dto);

	Area save(SaveAreaDto dto, Area area);
	
	void deleteById(Long id);
	
	public List<Area> findAll();
}
