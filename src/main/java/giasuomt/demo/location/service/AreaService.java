package giasuomt.demo.location.service;

import java.util.List;

import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.util.concurrent.ExecutionError;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.learnerAndRegister.model.LearnerAndRegister;
import giasuomt.demo.location.dto.CreateAreaDTO;
import giasuomt.demo.location.dto.FindingDtoArea;
import giasuomt.demo.location.dto.UpdateAreaDTO;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.location.repository.AreaRepository;


@Service
public class AreaService extends GenericService<Area, Long> implements IareaService {
	@Autowired
	private AreaRepository repository;
	@Autowired
	private MapDtoToModel mapper;
	
	private Logger logger=LoggerFactory.getLogger(AreaService.class);
	
	
	//API TRẢ VỀ LIST TẤT CẢ ((Ignore List Account))
	public List<Area> findAll() {
		return repository.findAll();
	}
	
	//POST
	public Area save(CreateAreaDTO dto) {
		Area area=new Area();
		
		try {
			area=(Area) mapper.map(dto, area);
			logger.info(String.format("Area is saved"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
		
		return repository.save(area);
		
	}
	//Delete
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
	//check id exits
	@Override
	public boolean checkExistIdofArea(Long id) {
		// TODO Auto-generated method stub
		return repository.countById(id)>=1;
	}
	//Update
	@Override
	public Area update(UpdateAreaDTO dto) {
		// TODO Auto-generated method stub
		Area areaUpdate=repository.getOne(dto.getIdArea());
		try {
			
			
			areaUpdate=(Area)mapper.map(dto, areaUpdate);
			
			logger.info(String.format("Area is updated"));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return repository.save(areaUpdate);
		
	}
	//findByNationAndProvincialLevelAndDistrictAndCommune
	@Override 
	public List<Area> findByNationAndProvincialLevelAndDistrictAndCommune(FindingDtoArea dtoArea) {
		// TODO Auto-generated method stub
		return repository.findByNationAndProvincialLevelAndDistrictAndCommune(dtoArea.getNation(), 
				dtoArea.getProvincialLevel(),dtoArea.getDistrict(), dtoArea.getCommune());
	}
	
	
	public Set<LearnerAndRegister> findLearnerAndRegistersById(Long areaId) {
		Optional<Area> areas = repository.findById(areaId);
		Set<LearnerAndRegister> learnerAndRegisters = areas.get().getLearnerAndRegisters();
		return learnerAndRegisters;
	}

	
	

}
