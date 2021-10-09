package giasuomt.demo.tutor.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.location.repository.IAreaRepository;
import giasuomt.demo.tutor.dto.SaveStudentDto;
import giasuomt.demo.tutor.dto.SaveTutorDto;
import giasuomt.demo.tutor.dto.TutorWithStudent;
import giasuomt.demo.tutor.dto.UpdateStudentDto;
import giasuomt.demo.tutor.dto.UpdateTutorDto;
import giasuomt.demo.tutor.model.GraduatedStudent;
import giasuomt.demo.tutor.model.InstitutionTeacher;
import giasuomt.demo.tutor.model.SchoolTeacher;
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
public class TutorService extends GenericService<Tutor, Long> implements ITutorService {

	private ITutorRepository iTutorRepository;

	private MapDtoToModel mapDtoToModel;

	// Service

	private IGraduatedStudentService iGraduatedStudentService;

	private IInsitutionTeacherService iInsitutionTeacherService;

	private ISchoolTeacherService iSchoolTeacherService;

	private IStudentService iStudentService;

	// Repository

	private IAreaRepository iAreaRepository;

	private IStudentRepository iStudentRepository;

	private ISchoolTeacherRepository iSchoolTeacherRepository;

	private IInstitutionTeacherRepository iInsitutionTeacherRepository;

	private IGraduatedStudentRepository iGraduatedStudentRepository;

	@Override
	public List<Tutor> findAll() {

		return iTutorRepository.findAll();
	}

	@Override
	public Tutor create(SaveTutorDto dto) {
		Tutor tutor = new Tutor();

		tutor.setTutorCode("new tutor code");

		tutor = save(dto, tutor);

		return tutor;
	}

	@Override
	public Tutor update(SaveTutorDto dto) {
		Tutor tutor = iTutorRepository.getOne(dto.getTutorId());

		if (tutor == null)
			return null;

		tutor = save(dto, tutor);

		return tutor;
	}

	@Override
	public Tutor save(SaveTutorDto dto, Tutor tutor) {

		try {
			// MAP DTO TO MODEL
			tutor = (Tutor) mapDtoToModel.map(dto, tutor);

			Optional<Area> tempArea = Optional.ofNullable(iAreaRepository.getOne(dto.getTempAreaId()));
			if (tempArea.isPresent())
				tutor.setTempArea(tempArea.get());

			Optional<Area> perArea = Optional.ofNullable(iAreaRepository.getOne(dto.getPerAreaId()));
			if (perArea.isPresent())
				tutor.setPerArea(perArea.get());

			Optional<Area> relArea = Optional.ofNullable(iAreaRepository.getOne(dto.getRelAreaId()));
			if (perArea.isPresent())
				tutor.setRelArea(relArea.get());

			// CREATE/UPDATE XUỐNG DB
			tutor = iTutorRepository.save(tutor);

			// DELETE/CREATE/UPDATE Ở CÁC TABLE CÓ QUAN HỆ
			Set<Student> students = tutor.getStudents(); // Delete
			for (Student student : students) {
				Boolean deleteThisStudent = true;
				Set<SaveStudentDto> saveStudentDtos = dto.getSaveStudentDtos();
				for (SaveStudentDto saveStudentDto : saveStudentDtos) {
					if (student.getId() == saveStudentDto.getStudentId())
						deleteThisStudent = false;
				}
				if (deleteThisStudent)
					iStudentService.delete(student.getId());
			}
			Set<SaveStudentDto> saveStudentDtos = dto.getSaveStudentDtos();
			for (SaveStudentDto saveStudentDto : saveStudentDtos) {
				if (saveStudentDto.getStudentId() == 0 || saveStudentDto.getStudentId() == null) { // Create
					saveStudentDto.setTutorId(tutor.getId());
					iStudentService.create(saveStudentDto);
				} else { // Update
					iStudentService.update(saveStudentDto);
				}
			}

			// Task: viết tương tự như trên cho GraduatedStudent, InstitutionTeacher,
			// SchoolTeacher

			return tutor;

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	@Override
	public void delete(Long id) {

		try {

			// Student ID
			Set<Long> studentIds = iStudentRepository.findStudentIdByTutorId(id);
			for (Long studentId : studentIds) {
				iStudentRepository.deleteById(studentId);

			}

			// School Teacher Id
			Set<Long> schoolTeacherIds = iSchoolTeacherRepository.findSchoolTeacherIdByTutorId(id);
			for (Long schoolTeacherId : schoolTeacherIds) {
				iSchoolTeacherRepository.deleteById(schoolTeacherId);
			}

			// Graduated Student
			Set<Long> graduatedStudentIds = iGraduatedStudentRepository.findGraduatedStudentIdByTutorId(id);
			for (Long graduatedStudentId : graduatedStudentIds) {
				iGraduatedStudentRepository.deleteById(graduatedStudentId);
			}

			// Institution Teacher
			Set<Long> institutionTeacherIds = iInsitutionTeacherRepository.findInstitutionTeacherIdByTutorId(id);
			for (Long institutionTeacherId : institutionTeacherIds) {
				iInsitutionTeacherRepository.deleteById(institutionTeacherId);
			}

			iTutorRepository.deleteById(id);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return;

	}

	// StudentWithDto

	private void ModelToDto(Tutor tutor, TutorWithStudent tutorWithStudent) {

		// Set<Student> students =
		// studentRepository.findByallStudent(tutor.getTutorCode());
		mapDtoToModel.map(tutor, tutorWithStudent);
		// tutorWithStudent.setStudents(null);

	}

	private List<TutorWithStudent> MaptoList(List<Tutor> students) {

		List<TutorWithStudent> tutorWithStudents = new LinkedList<>();
		for (Tutor tutor : students) {
			TutorWithStudent student = new TutorWithStudent();
			ModelToDto(tutor, student);
			tutorWithStudents.add(student);

		}

		return tutorWithStudents;

	}

	public List<TutorWithStudent> findalll() {

		List<Tutor> list = iTutorRepository.findAll();
		List<TutorWithStudent> res = MaptoList(list);
		return res;
	}

}
