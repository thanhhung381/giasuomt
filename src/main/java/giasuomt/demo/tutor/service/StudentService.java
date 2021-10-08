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
import giasuomt.demo.location.repository.IAreaRepository;
import giasuomt.demo.location.service.IAreaService;
import giasuomt.demo.tutor.dto.CreateStudentDto;
import giasuomt.demo.tutor.dto.UpdateStudentDto;
import giasuomt.demo.tutor.model.Student;
import giasuomt.demo.tutor.model.Tutor;
import giasuomt.demo.tutor.repository.IGraduatedStudentRepository;
import giasuomt.demo.tutor.repository.IInstitutionTeacherRepository;
import giasuomt.demo.tutor.repository.ISchoolTeacherRepository;
import giasuomt.demo.tutor.repository.IStudentRepository;
import giasuomt.demo.tutor.repository.ITutorRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentService extends GenericService<Student, Long> implements IStudentService {

	// Repository
	private IStudentRepository studentRepository;

	private ITutorRepository tutorRepository;

	// model mapper
	private ModelMapper mapper;

	private MapDtoToModel mapDtoToModel;

	@Override
	public List<Student> findAll() {

		return studentRepository.findAll();
	}

	// Save Student
	@Override
	public Student save(CreateStudentDto dto) {

		try {
			Student student = new Student();
			student = mapper.map(dto, student.getClass());

			Optional<Tutor> tutor = Optional.ofNullable(tutorRepository.getOne(dto.getTutorId()));
			if (tutor.isPresent())
				student.setTutor(tutor.get());

			return studentRepository.save(student);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	// Delete Student
	@Override
	public void deleteById(Long id) {

		try {

			studentRepository.deleteById(id);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return;
	}

	// Update Student
	@Override
	public Student update(UpdateStudentDto dto, Long id) {
		try {

			Student studentUpdate = studentRepository.getOne(id);

			studentUpdate = (Student) mapDtoToModel.map(dto, studentUpdate);

			Optional<Tutor> tutor = Optional.ofNullable(tutorRepository.getOne(dto.getIdTutor()));
			if (tutor.isPresent())
				studentUpdate.setTutor(tutor.get());

			return studentRepository.save(studentUpdate);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

}
