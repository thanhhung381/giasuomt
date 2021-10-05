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
import giasuomt.demo.location.repository.AreaRepository;

@Service
public class AreaService extends GenericService<Area, Long> implements IAreaService {
	@Autowired
	private AreaRepository repository;
	@Autowired
	private ModelMapper mapper;

	private Logger logger = LoggerFactory.getLogger(AreaService.class);

	// API TRẢ VỀ LIST TẤT CẢ ((Ignore List Account))
	public List<Area> findAll() {
		
		return repository.findAll();
		
	}

	// POST
	public Area save(CreateAreaDTO dto) {

		try {
			Area area = new Area();
			area =  mapper.map(dto, area.getClass());
			logger.info(String.format("Area is saved"));

			return repository.save(area);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}

		return null;

	}

	// Delete
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		try {
			repository.deleteById(id);
			logger.info(String.format("Area is deleted"));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.debug("Don't have any Id in your Data ");
		}

	}

	// check id exits
	@Override
	public boolean checkExistIdofArea(Long id) {
		// TODO Auto-generated method stub
		return repository.countById(id) >= 1;
	}

	// Update
	@Override
	public Area update(UpdateAreaDTO dto) {
		// TODO Auto-generated method stub

		try {

			Area areaUpdate = repository.getOne(dto.getIdArea());
			areaUpdate =  mapper.map(dto, areaUpdate.getClass());

			logger.info(String.format("Area is updated"));
			return repository.save(areaUpdate);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;

	}

	// findByNationAndProvincialLevelAndDistrictAndCommune
	@Override
	public List<Area> findByNationAndProvincialLevelAndDistrictAndCommune(FindingDtoArea dtoArea) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return repository.countByProvincialLevel(provincialLevel) >= 1;
	}

	@Override
	public boolean checkExistDistrict(String district) {
		// TODO Auto-generated method stub
		return repository.countByDistrict(district) >= 1;
	}

	@Override
	public boolean checkExistCommune(String commune) {
		// TODO Auto-generated method stub
		return repository.countByCommune(commune) >= 1;
	}

}