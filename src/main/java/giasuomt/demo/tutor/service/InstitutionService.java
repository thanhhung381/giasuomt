package giasuomt.demo.tutor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.tutor.model.Tutor;
import giasuomt.demo.tutor.repository.ITutorRepository;

@Service
public class InstitutionService extends GenericService<Tutor, Long> implements IInstitutionService{
	@Autowired
	private IInstitutionRepository repository;
	@Autowired
	private MapDtoToModel mapper;
	
	
}
