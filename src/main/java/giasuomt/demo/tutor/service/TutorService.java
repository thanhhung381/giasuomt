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
import giasuomt.demo.location.repository.AreaRepository;
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
import giasuomt.demo.tutor.repository.GraduatedStudentRepository;
import giasuomt.demo.tutor.repository.InstitutionTeacherRepository;
import giasuomt.demo.tutor.repository.SchoolTeacherRepository;
import giasuomt.demo.tutor.repository.StudentRepository;
import giasuomt.demo.tutor.repository.TutorRepository;

@Service
public class TutorService extends GenericService<Tutor, Long> implements ITutorService {

	private Logger logger = LoggerFactory.getLogger(TutorService.class);

	@Autowired
	private TutorRepository tutorRepository;

	@Autowired
	private MapDtoToModel mapDtoToModel;

	// Service
	@Autowired
	private IGraduatedStudentService graduatedStudentService;

	@Autowired
	private IInsitutionTeacherService iInsitutionTeacherService;

	@Autowired
	private ISchoolTeacherService iSchoolTeacherService;

	@Autowired
	private IStudentService iStudentService;

	@Autowired
	private ModelMapper modelMapper;

	// Repository
	@Autowired
	private AreaRepository areaRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private SchoolTeacherRepository schoolTeacherRepository;
	
	@Autowired
	private InstitutionTeacherRepository insitutionTeacherRepository;
	
	@Autowired
	private GraduatedStudentRepository graduatedStudentRepository;
	
	
	
	@Override
	public List<Tutor> findAll() {
		// TODO Auto-generated method stub
		return tutorRepository.findAll();
	}

	// luu
	@Override
	public Tutor save(CreateTutorDto dto) {
		// TODO Auto-generated method stub

		try {

			Tutor tutor = new Tutor();
			tutor = (Tutor) mapDtoToModel.map(dto, tutor);

			Optional<Area> tempArea = Optional.ofNullable(areaRepository.getOne(dto.getTempAreaId()));
			if (tempArea.isPresent())
				tutor.setTempArea(tempArea.get());

			Optional<Area> perArea = Optional.ofNullable(areaRepository.getOne(dto.getPerAreaId()));
			if (perArea.isPresent())
				tutor.setPerArea(perArea.get());

			Optional<Area> relArea = Optional.ofNullable(areaRepository.getOne(dto.getRelAreaId()));
			if (perArea.isPresent())
				tutor.setRelArea(relArea.get());

			logger.info("Tutor is saved");

			tutor = tutorRepository.save(tutor);

			// Graduated Student
			Set<CreateGraduatedStudentDto> graduatedStudents = dto.getCreateGraduatedStudentDtos();
			for (CreateGraduatedStudentDto createGraduatedStudentDto : graduatedStudents) {
				GraduatedStudent graduatedStudent = new GraduatedStudent();
				graduatedStudent = (GraduatedStudent) mapDtoToModel.map(createGraduatedStudentDto, graduatedStudent);
				graduatedStudent.setTutor(tutor);
				graduatedStudentService.save(graduatedStudent);
//				createGraduatedStudentDto.setTutorId(tutor.getId());
//				graduatedStudents.add(createGraduatedStudentDto);
//				graduatedStudentService.save(createGraduatedStudentDto);
			}

			// Student
			Set<CreateStudentDto> createStudentDtos = dto.getCreateStudentDtos();
			for (CreateStudentDto createStudentDto : createStudentDtos) {
				Student student = new Student();
				student = (Student) mapDtoToModel.map(createStudentDto, student);
				student.setTutor(tutor);
				iStudentService.save(student);
//				createStudentDto.setTutorId(tutor.getId());
//				createStudentDtos.add(createStudentDto);
//				iStudentService.save(createStudentDto);
			}

			// Institution Teacher
			Set<CreateInstitutionTeacherDto> institutionTeachers = dto.getCreateInstitutionTeacherDtos();
			for (CreateInstitutionTeacherDto createInstitutionTeacherDto : institutionTeachers) {
				InstitutionTeacher institutionTeacher = new InstitutionTeacher();
				institutionTeacher = (InstitutionTeacher) mapDtoToModel.map(createInstitutionTeacherDto, institutionTeacher);
				institutionTeacher.setTutor(tutor);
				iInsitutionTeacherService.save(institutionTeacher);
//				createInstitutionTeacherDto.setTutorId(tutor.getId());
//				institutionTeachers.add(createInstitutionTeacherDto);
//				iInsitutionTeacherService.save(createInstitutionTeacherDto);
			}

			// School Teacher
			Set<CreateSchoolTeacherDto> schoolTeachers = dto.getCreateSchoolTeacherDtos();
			for (CreateSchoolTeacherDto createSchoolTeacherDto : schoolTeachers) {
				SchoolTeacher schoolTeacher = new SchoolTeacher();
				schoolTeacher = (SchoolTeacher) mapDtoToModel.map(createSchoolTeacherDto, schoolTeacher);
				schoolTeacher.setTutor(tutor);
				iSchoolTeacherService.save(schoolTeacher);
//				createSchoolTeacherDto.setTutorId(tutor.getId());
//				schoolTeachers.add(createSchoolTeacherDto);
//				iSchoolTeacherService.save(createSchoolTeacherDto);

			}

			return tutor;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("Have something wrong :(");

		}

		return null;

	}

	// DELETE
	@Override
	public void deleteById(Long idTutor) {

		try {
			
			// Student ID
			Set<Long> studentIds = studentRepository.findStudentIdByTutorId(idTutor);
			for (Long studentId : studentIds) {
				studentRepository.deleteById(studentId);

			}
			
			// School Teacher Id
			Set<Long> schoolTeacherIds=schoolTeacherRepository.findSchoolTeacherIdByTutorId(idTutor);
			for (Long schoolTeacherId : schoolTeacherIds) {
					schoolTeacherRepository.deleteById(idTutor);
			}
			
			// Graduated Student
			Set<Long> graduatedStudentIds=graduatedStudentRepository.findGraduatedStudentIdByTutorId(idTutor);
			for (Long graduatedStudentId : graduatedStudentIds) {
				graduatedStudentRepository.deleteById(idTutor);
			}
			
			// Institution Teacher 
			Set<Long> institutionTeacherIds=insitutionTeacherRepository.findInstitutionTeacherIdByTutorId(idTutor);
			for (Long institutionTeacherId : institutionTeacherIds) {
				insitutionTeacherRepository.deleteById(idTutor);
			}
			
			
			

			tutorRepository.deleteById(idTutor);

			logger.info("Tutor is  deleted");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("Have something wrong :(");
		}
		return;

	}

	// Update
	@Override
	public Tutor update(UpdateTutorDto dto, Long id) {
		// TODO Auto-generated method stub
		try {

			
			// Update Student
			Set<Long> studentIds=studentRepository.findStudentIdByTutorId(id);
			Set<UpdateStudentDto> studentDtos=dto.getUpdateStudentDtos();
			
			for (UpdateStudentDto updateStudentDto : studentDtos) {
				for (Long studentId : studentIds) {
					
					iStudentService.update(updateStudentDto, studentId);
					
				}
			}
			
			
			
			Tutor updateTutor = tutorRepository.getOne(id);

			updateTutor = (Tutor) mapDtoToModel.map(dto, updateTutor);

			Optional<Area> tempArea = Optional.ofNullable(areaRepository.getOne(dto.getTempAreaId()));
			if (tempArea.isPresent())
				updateTutor.setTempArea(tempArea.get());

			Optional<Area> perArea = Optional.ofNullable(areaRepository.getOne(dto.getPerAreaId()));
			if (perArea.isPresent())
				updateTutor.setPerArea(perArea.get());

			Optional<Area> relArea = Optional.ofNullable(areaRepository.getOne(dto.getRelAreaId()));
			if (perArea.isPresent())
				updateTutor.setRelArea(relArea.get());
			updateTutor = tutorRepository.save(updateTutor);
			logger.info("Tutor is updated");

			return updateTutor;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("Have something wrong :(");
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
