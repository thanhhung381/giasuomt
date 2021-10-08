package giasuomt.demo.tutor.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.validation.Valid;

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
import giasuomt.demo.location.service.IAreaService;
import giasuomt.demo.tutor.dto.CreateStudentDto;
import giasuomt.demo.tutor.dto.UpdateStudentDto;
import giasuomt.demo.tutor.model.Student;
import giasuomt.demo.tutor.model.Tutor;
import giasuomt.demo.tutor.repository.StudentRepository;
import giasuomt.demo.tutor.repository.TutorRepository;

@Service
public class StudentService extends GenericService<Student, Long> implements IStudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private TutorRepository tutorRepository;

	@Autowired
	private MapDtoToModel mapDtoToModel;
	
	
	private Logger logger=LoggerFactory.getLogger(StudentService.class);
	
	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	// SAVE TUTOR
	@Override
	public Student save(CreateStudentDto dto) {
		// TODO Auto-generated method stub

		try {
			Student student = new Student();
			student = mapper.map(dto, student.getClass());

			Optional<Tutor> tutor = Optional.ofNullable(tutorRepository.getOne(dto.getTutorId()));
			if (tutor.isPresent())
				student.setTutor(tutor.get());
			
			logger.info("Student is saved");
			
			return studentRepository.save(student);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			logger.info("Have something wrong :(");
		}

		return null;

	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		try {

			studentRepository.deleteById(id);
			logger.info("Student is deleted");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("Have something wrong :(");
		}
		return;
	}

	@Override
	public Student update(UpdateStudentDto dto, Long id) {
		try {

			Student studentUpdate = studentRepository.getOne(id);
			
			studentUpdate=(Student)mapDtoToModel.map(dto, studentUpdate);
			
			
			Optional<Tutor> tutor=Optional.ofNullable(tutorRepository.getOne(dto.getIdTutor()));
			if(tutor.isPresent())
				studentUpdate.setTutor(tutor.get());
					
			
			logger.info("Student is updated");
			

			return studentRepository.save(studentUpdate);

		} catch (Exception e) { // TODO: handle exception
			e.printStackTrace();
			logger.info("Have something wrong :(");
		}

		return null;
	}

}
