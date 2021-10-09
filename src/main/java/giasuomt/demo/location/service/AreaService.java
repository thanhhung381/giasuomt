package giasuomt.demo.location.service;

import java.util.List;


import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.learnerAndRegister.model.LearnerAndRegister;
import giasuomt.demo.location.dto.CreateAreaDTO;
import giasuomt.demo.location.dto.FindingDtoArea;
import giasuomt.demo.location.dto.UpdateAreaDTO;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.location.repository.IAreaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AreaService extends GenericService<Area, Long> implements IAreaService {
	
	
	
	private IAreaRepository repository;

	private MapDtoToModel mapper;

	

	// API TRẢ VỀ LIST TẤT CẢ ((Ignore List Account))
	public List<Area> findAll() {

		return repository.findAll();

	}

	// POST
	public Area save(CreateAreaDTO dto) {

		try {
			Area area = new Area();
			area = (Area) mapper.map(dto, area);
		

			return repository.save(area);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	// Delete
	@Override
	public void deleteById(Long id) {

		try {
			repository.deleteById(id);
			

		} catch (Exception e) {

			e.printStackTrace();
		
		}

	}

	// check id exits
	@Override
	public boolean checkExistIdofArea(Long id) {

		return repository.countById(id) >= 1;
	}

	// Update
	@Override
	public Area update(UpdateAreaDTO dto) {

		try {

			Area areaUpdate = repository.getOne(dto.getIdArea());
			areaUpdate = (Area) mapper.map(dto, areaUpdate);

		
			return repository.save(areaUpdate);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;

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
				return repository.findByNationAndProvincialLevelAndDistrictAndCommune(nation, provincialLevel, district,
						commune, state);
			}
			if (state != null && provincialLevel != null && district != null) {
				return repository.findByNationAndProvincialLevelAndStateAndDistrict(nation, provincialLevel, state,
						district);
			}
			if (state != null && provincialLevel != null) {
				return repository.findByNationAndProvincialLevelAndState(nation, provincialLevel, state);
			} else if (state != null) {
				return repository.findByNationAndState(nation, state);
			} else
				return repository.findByNation(nation);
		} else

			return null;

	}

	public Set<LearnerAndRegister> findLearnerAndRegistersById(Long areaId) {
		/*
		 * Optional<Area> areas = repository.findById(areaId); Set<LearnerAndRegister>
		 * learnerAndRegisters = areas.get().getLearnerAndRegisters(); return
		 * learnerAndRegisters;
		 */
		return null;
	}

	@Override
	public boolean checkExistProvincialLevel(String provincialLevel) {

		return repository.countByProvincialLevel(provincialLevel) >= 1;
	}

	@Override
	public boolean checkExistDistrict(String district) {

		return repository.countByDistrict(district) >= 1;
	}

	@Override
	public boolean checkExistCommune(String commune) {

		return repository.countByCommune(commune) >= 1;
	}

}