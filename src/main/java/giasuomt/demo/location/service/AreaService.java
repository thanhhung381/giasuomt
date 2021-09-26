package giasuomt.demo.location.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import giasuomt.demo.learnerAndRegister.model.LearnerAndRegister;
import giasuomt.demo.location.dto.CreateAreaDTO_Boss;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.location.repository.AreaRepository;


@Service
public class AreaService {
	@Autowired
	private AreaRepository repository;
	
	//API TRẢ VỀ LIST TẤT CẢ ((Ignore List Account))
	public List<Area> findAll() {
		return repository.findAll();
	}
	
	public Set<LearnerAndRegister> findLearnerAndRegistersById(Long areaId) {
		Optional<Area> areas = repository.findById(areaId);
		Set<LearnerAndRegister> learnerAndRegisters = areas.get().getLearnerAndRegisters();
		return learnerAndRegisters;
	}
	
	
	
	
	//POST
	public Area save(CreateAreaDTO_Boss dto) {
		Area area = new Area()
					.nation(dto.getNation())
				    .state(dto.getState())
				    .provincialLevel(dto.getProvincialLevel())
				    .district(dto.getDistrict())
				    .commune(dto.getCommune())
				    .xRelCoo(dto.getxRelCoo())
					.yRelCoo(dto.getyRelCoo());
		repository.save(area);
		return area;
	}
}
