package giasuomt.demo.tutor.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.tutor.dto.CreateInstitutionTeacherDto;
import giasuomt.demo.tutor.model.InstitutionTeacher;
import giasuomt.demo.tutor.model.Tutor;
import giasuomt.demo.tutor.repository.InstitutionTeacherRepository;
import giasuomt.demo.tutor.repository.TutorRepository;

@Service
public class InsitutionTeacherService extends GenericService<InstitutionTeacher, Long> implements IInsitutionTeacherService {
	@Autowired
	private InstitutionTeacherRepository insitutionTeacherRepository;
	@Autowired
	private MapDtoToModel mapper;
	@Autowired
	private TutorRepository tutorRepository;
	
	
	@Override
	public InstitutionTeacher save(CreateInstitutionTeacherDto dto) {
		// TODO Auto-generated method stub
		
		InstitutionTeacher institutionTeacher=new InstitutionTeacher();
		institutionTeacher=(InstitutionTeacher)mapper.map(dto, institutionTeacher);
			
			Optional<Tutor> tutor=Optional.ofNullable(tutorRepository.getOne(dto.getTutorId()));
				if(tutor.isPresent())
					institutionTeacher.setTutor(tutor.get());
		
		return insitutionTeacherRepository.save(institutionTeacher);
	}
	
	
	
}
