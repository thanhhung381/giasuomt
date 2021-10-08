package giasuomt.demo.tutor.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.tutor.dto.CreateSchoolTeacherDto;
import giasuomt.demo.tutor.model.SchoolTeacher;
import giasuomt.demo.tutor.model.Tutor;
import giasuomt.demo.tutor.repository.InstitutionTeacherRepository;
import giasuomt.demo.tutor.repository.SchoolTeacherRepository;
import giasuomt.demo.tutor.repository.TutorRepository;

@Service
public class SchoolTeacherService extends GenericService<SchoolTeacher, Long> implements ISchoolTeacherService {
	
	@Autowired
	private SchoolTeacherRepository schoolTeacherRepository;
	@Autowired
	private MapDtoToModel mapper;
	@Autowired
	private TutorRepository tutorRepository;
	

	@Override
	public SchoolTeacher save(CreateSchoolTeacherDto dto) {
		// TODO Auto-generated method stub
		 SchoolTeacher schoolTeacher=new SchoolTeacher();
		 schoolTeacher=(SchoolTeacher)mapper.map(dto, schoolTeacher);
		 	Optional<Tutor> tutor=Optional.ofNullable(tutorRepository.getOne(dto.getTutorId()));
		 	if(tutor.isPresent())
		 		schoolTeacher.setTutor(tutor.get());
		 
		 
		 
		 
		return super.save(schoolTeacher);
		
	}
	
	
	
	
	
}
