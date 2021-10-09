package giasuomt.demo.location.service;
import java.util.List;
import java.util.Set;
import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.learnerAndRegister.model.LearnerAndRegister;
import giasuomt.demo.location.dto.FindingDtoArea;
import giasuomt.demo.location.dto.SaveAreaDTO;
import giasuomt.demo.location.model.Area;

public interface IAreaService extends IGenericService<Area, Long> {
	Area create(SaveAreaDTO dto);
	
	Area update(SaveAreaDTO dto);
	
	Area save(SaveAreaDTO dto, Area area);

	
	
	boolean checkExistIdofArea(Long id);
	
	boolean checkExistProvincialLevel(String provincialLevel);
	boolean checkExistDistrict(String district);
	boolean checkExistCommune(String commune);
		
	List<Area> findByNationAndProvincialLevelAndDistrictAndCommune(FindingDtoArea dtoArea);

	Set<LearnerAndRegister> findLearnerAndRegistersById(Long areaId);

}
