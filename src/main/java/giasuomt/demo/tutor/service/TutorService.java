package giasuomt.demo.tutor.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.location.dto.FindingDtoArea;
import giasuomt.demo.location.dto.UpdateAreaDTO;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.location.repository.IAreaRepository;
import giasuomt.demo.location.service.IAreaService;
import giasuomt.demo.tutor.dto.CreateGraduatedStudentDto;
import giasuomt.demo.tutor.dto.CreateInstitutionTeacherDto;
import giasuomt.demo.tutor.dto.CreateSchoolTeacherDto;
import giasuomt.demo.tutor.dto.CreateStudentDto;
import giasuomt.demo.tutor.dto.CreateTutorDto;
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

	

	private ITutorRepository tutorRepository;

	private MapDtoToModel mapDtoToModel;

	// Service

	private IGraduatedStudentService iGraduatedStudentService;

	private IInsitutionTeacherService iInsitutionTeacherService;

	private ISchoolTeacherService iSchoolTeacherService;

	private IStudentService iStudentService;

	private ModelMapper modelMapper;

	// Repository

	private IAreaRepository iAreaRepository;

	private IStudentRepository iStudentRepository;

	private ISchoolTeacherRepository iSchoolTeacherRepository;

	private IInstitutionTeacherRepository iInsitutionTeacherRepository;

	private IGraduatedStudentRepository iGraduatedStudentRepository;

	@Override
	public List<Tutor> findAll() {

		return tutorRepository.findAll();
	}

	// luu
	@Override
	public Tutor save(CreateTutorDto dto) {

		try {

			Tutor tutor = new Tutor();
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

			

			tutor = tutorRepository.save(tutor);

			// Graduated Student
			Set<CreateGraduatedStudentDto> graduatedStudents = dto.getCreateGraduatedStudentDtos();
			for (CreateGraduatedStudentDto createGraduatedStudentDto : graduatedStudents) {

				graduatedStudents.add(createGraduatedStudentDto);
				iGraduatedStudentService.save(createGraduatedStudentDto);
			}

			// Student
			Set<CreateStudentDto> createStudentDtos = dto.getCreateStudentDtos();
			for (CreateStudentDto createStudentDto : createStudentDtos) {

				createStudentDtos.add(createStudentDto);
				iStudentService.save(createStudentDto);
			}

			// Institution Teacher
			Set<CreateInstitutionTeacherDto> institutionTeachers = dto.getCreateInstitutionTeacherDtos();
			for (CreateInstitutionTeacherDto createInstitutionTeacherDto : institutionTeachers) {

				institutionTeachers.add(createInstitutionTeacherDto);
				iInsitutionTeacherService.save(createInstitutionTeacherDto);
			}

			// School Teacher
			Set<CreateSchoolTeacherDto> schoolTeachers = dto.getCreateSchoolTeacherDtos();
			for (CreateSchoolTeacherDto createSchoolTeacherDto : schoolTeachers) {

				schoolTeachers.add(createSchoolTeacherDto);
				iSchoolTeacherService.save(createSchoolTeacherDto);

			}

			return tutor;

		} catch (Exception e) {

			e.printStackTrace();
		

		}

		return null;

	}

	// DELETE
	@Override
	public void deleteById(Long idTutor) {

		try {

			// Student ID
			Set<Long> studentIds = iStudentRepository.findStudentIdByTutorId(idTutor);
			for (Long studentId : studentIds) {
				iStudentRepository.deleteById(studentId);

			}

			// School Teacher Id
			Set<Long> schoolTeacherIds = iSchoolTeacherRepository.findSchoolTeacherIdByTutorId(idTutor);
			for (Long schoolTeacherId : schoolTeacherIds) {
				iSchoolTeacherRepository.deleteById(schoolTeacherId);
			}

			// Graduated Student
			Set<Long> graduatedStudentIds = iGraduatedStudentRepository.findGraduatedStudentIdByTutorId(idTutor);
			for (Long graduatedStudentId : graduatedStudentIds) {
				iGraduatedStudentRepository.deleteById(graduatedStudentId);
			}

			// Institution Teacher
			Set<Long> institutionTeacherIds = iInsitutionTeacherRepository.findInstitutionTeacherIdByTutorId(idTutor);
			for (Long institutionTeacherId : institutionTeacherIds) {
				iInsitutionTeacherRepository.deleteById(institutionTeacherId);
			}

			tutorRepository.deleteById(idTutor);

	
		} catch (Exception e) {

			e.printStackTrace();
			
		}
		return;

	}

	// Update
	@Override
	public Tutor update(UpdateTutorDto dto, Long id) {

		try {

			// Update Student
			Set<Long> studentIds = iStudentRepository.findStudentIdByTutorId(id);
			Set<UpdateStudentDto> studentDtos = dto.getUpdateStudentDtos();

			for (UpdateStudentDto updateStudentDto : studentDtos) {
				for (Long studentId : studentIds) {

					iStudentService.update(updateStudentDto, studentId);

				}
			}

			Tutor updateTutor = tutorRepository.getOne(id);

			updateTutor = (Tutor) mapDtoToModel.map(dto, updateTutor);

			Optional<Area> tempArea = Optional.ofNullable(iAreaRepository.getOne(dto.getTempAreaId()));
			if (tempArea.isPresent())
				updateTutor.setTempArea(tempArea.get());

			Optional<Area> perArea = Optional.ofNullable(iAreaRepository.getOne(dto.getPerAreaId()));
			if (perArea.isPresent())
				updateTutor.setPerArea(perArea.get());

			Optional<Area> relArea = Optional.ofNullable(iAreaRepository.getOne(dto.getRelAreaId()));
			if (perArea.isPresent())
				updateTutor.setRelArea(relArea.get());
			updateTutor = tutorRepository.save(updateTutor);
			

			return updateTutor;
		} catch (Exception e) {

			e.printStackTrace();
			
		}

		return null;

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

		List<Tutor> list = tutorRepository.findAll();
		List<TutorWithStudent> res = MaptoList(list);
		return res;
	}

}
