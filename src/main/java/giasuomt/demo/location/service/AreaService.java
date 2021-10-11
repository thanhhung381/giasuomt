package giasuomt.demo.location.service;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.location.dto.FindingDtoArea;
import giasuomt.demo.location.dto.SaveAreaDto;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.location.repository.IAreaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AreaService extends GenericService<Area, Long> implements IAreaService {

	private IAreaRepository iAreaRepository;

	private MapDtoToModel mapper;

	@Override
	public List<Area> findAll() {
		return iAreaRepository.findAll();
	}

	@Override
	public Area create(@Valid SaveAreaDto dto) {
		Area area = new Area();

		return save(dto, area);
	}

	@Override
	public Area update(SaveAreaDto dto) {
		Area area = iAreaRepository.getOne(dto.getId());

		return save(dto, area);
	}

	@Override
	public Area save(SaveAreaDto dto, Area area) {
		try {
			// MAP DTO TO MODEL
			area = (Area) mapper.map(dto, area);

			return iAreaRepository.save(area);

		} catch (Exception e) {e.printStackTrace();}

		return null;
	}

	@Override
	public void deleteById(Long id) {
		try {
			iAreaRepository.deleteById(id);

		} catch (Exception e) {e.printStackTrace();}
	}

	
	
	
	
	
	
	
	// check id exits
	@Override
	public boolean checkExistIdofArea(Long id) {

		return iAreaRepository.countById(id) >= 1;
	}

	// findByNationAndProvincialLevelAndDistrictAndCommune
	@Override
	public List<Area> findByNationAndProvincialLevelAndDistrictAndCommune(FindingDtoArea dtoArea) {

		String nation = dtoArea.getNation();
		String state = dtoArea.getState();
		String provincialLevel = dtoArea.getProvincialLevel();
		String district = dtoArea.getDistrict();
		String commune = dtoArea.getCommune();

		if (nation != null) {
			if (state != null && provincialLevel != null && district != null && commune != null) {
				return iAreaRepository.findByNationAndProvincialLevelAndDistrictAndCommune(nation, provincialLevel,
						district, commune, state);
			}
			if (state != null && provincialLevel != null && district != null) {
				return iAreaRepository.findByNationAndProvincialLevelAndStateAndDistrict(nation, provincialLevel, state,
						district);
			}
			if (state != null && provincialLevel != null) {
				return iAreaRepository.findByNationAndProvincialLevelAndState(nation, provincialLevel, state);
			} else if (state != null) {
				return iAreaRepository.findByNationAndState(nation, state);
			} else
				return iAreaRepository.findByNation(nation);
		} else

			return null;

	}



	@Override
	public boolean checkExistProvincialLevel(String provincialLevel) {

		return iAreaRepository.countByProvincialLevel(provincialLevel) >= 1;
	}

	@Override
	public boolean checkExistDistrict(String district) {

		return iAreaRepository.countByDistrict(district) >= 1;
	}

	@Override
	public boolean checkExistCommune(String commune) {

		return iAreaRepository.countByCommune(commune) >= 1;
	}



}