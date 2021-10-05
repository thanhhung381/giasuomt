package giasuomt.demo.tutor.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

			Optional<Tutor> tutor = Optional.ofNullable(tutorRepository.getOne(dto.getIdTutor()));
			if (tutor.isPresent())
				student.setTutor(tutor.get());

			return super.save(student);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		try {

			studentRepository.deleteById(id);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return;
	}

	@Override
	public Student update(UpdateStudentDto dto, Long id) {
		try {

			Student studentUpdate = studentRepository.getOne(id);

			studentUpdate = mapper.map(dto, studentUpdate.getClass());

			studentUpdate.setId(dto.getIdTutor());

			return super.save(studentUpdate);

		} catch (Exception e) { // TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

}
