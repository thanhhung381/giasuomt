package giasuomt.demo.tutor.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.tutor.dto.CreateStudentDTO;
import giasuomt.demo.tutor.dto.CreateTutorDTO;
import giasuomt.demo.tutor.model.Student;
import giasuomt.demo.tutor.model.Tutor;
import giasuomt.demo.tutor.repository.ITutorRepository;

@Service
public class TutorService extends GenericService<Tutor, Long> implements ITutorService{
	@Autowired
	private ITutorRepository repository;
	@Autowired
	private MapDtoToModel mapper;
	
	@Override
	public Tutor saveTutor(CreateTutorDTO dto) {
		Tutor tutor = new Tutor();
		tutor = (Tutor) mapper.map(dto, tutor);  
		
		tutor.addTempAreaById(dto.getTempArea().getId());
		
		Set<CreateStudentDTO> createStudentDTOs = dto.getStudents();
		Set<Student> students = new HashSet<>();
		//Map CreateStudentDTOs to Students
		for(CreateStudentDTO createStudentDTO : createStudentDTOs) {
			//Map CreateStudentDTO to Student
			Student student = new Student();
			student = (Student) mapper.map(createStudentDTO, student);  
			student.addInstitutionById(createStudentDTO.getInstitutionId());
			student.addMajorById(createStudentDTO.getMajorId());
			//add Student vảo Set<Student>
			students.add(student);		
		}
		//Add Set<Student> vào Tutor
		tutor.addStudent(students);	
		
		return repository.save(tutor);
	}
}
