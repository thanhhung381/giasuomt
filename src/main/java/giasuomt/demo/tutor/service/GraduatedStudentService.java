package giasuomt.demo.tutor.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.tutor.dto.CreateGraduatedStudentDto;
import giasuomt.demo.tutor.model.GraduatedStudent;
import giasuomt.demo.tutor.model.Tutor;
import giasuomt.demo.tutor.repository.GraduatedStudentRepository;
import giasuomt.demo.tutor.repository.TutorRepository;

@Service
public class GraduatedStudentService extends GenericService<GraduatedStudent, Long> implements IGraduatedStudentService {
	
	@Autowired
	private GraduatedStudentRepository graduatedStudentRepository;
	@Autowired
	private MapDtoToModel mapper;
	@Autowired
	private TutorRepository tutorRepository;
	
	@Override
	public GraduatedStudent save(CreateGraduatedStudentDto dto) {
		// TODO Auto-generated method stub
		
		GraduatedStudent graduatedStudent=new GraduatedStudent();
		graduatedStudent=(GraduatedStudent)mapper.map(dto, graduatedStudent);
		
		Optional<Tutor> tutor= Optional.ofNullable(tutorRepository.getOne(dto.getIdTutor()));
		if(tutor.isPresent())
			graduatedStudent.setTutor(tutor.get());
		
		
		return super.save(graduatedStudent);
	}
	
	
}
