package giasuomt.demo.tutor.service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.location.repository.IAreaRepository;
import giasuomt.demo.tutor.dto.SaveGraduatedStudentDto;
import giasuomt.demo.tutor.dto.SaveInstitutionTeacherDto;
import giasuomt.demo.tutor.dto.SaveSchoolTeacherDto;
import giasuomt.demo.tutor.dto.SaveStudentDto;
import giasuomt.demo.tutor.dto.SaveTutorDto;
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

	private IInstitutionTeacherService iInstitutionTeacherService;

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
		
		//CREATE Ở CÁC TABLE CÓ THAM CHIẾU TỚI MODEL
		Set<Student> students = new HashSet<>();
		Set<SaveStudentDto> saveStudentDtos = dto.getSaveStudentDtos();
		for (SaveStudentDto saveStudentDto : saveStudentDtos) {
			saveStudentDto.setTutorId(tutor.getId());
			Student student = iStudentService.create(saveStudentDto);          // Create	
			students.add(student); //For return
		}
		tutor.setStudents(students); //For return
		
		Set<GraduatedStudent> graduatedStudents = new HashSet<>();
		for (SaveGraduatedStudentDto saveGraduatedStudentDto : dto.getSaveGraduatedStudentDtos()) {
			saveGraduatedStudentDto.setTutorId(tutor.getId());
			GraduatedStudent graduatedStudent = iGraduatedStudentService.create(saveGraduatedStudentDto);          // Create	
			graduatedStudents.add(graduatedStudent); //For return
		}
		tutor.setGraduatedStudents(graduatedStudents); //For return
		
		Set<InstitutionTeacher> institutionTeachers = new HashSet<>();
		for (SaveInstitutionTeacherDto saveInstitutionTeacherDto : dto.getSaveInstitutionTeacherDtos()) {
			saveInstitutionTeacherDto.setTutorId(tutor.getId());
			InstitutionTeacher institutionTeacher = iInstitutionTeacherService.create(saveInstitutionTeacherDto);          // Create	
			institutionTeachers.add(institutionTeacher); //For return
		}
		tutor.setInstitutionTeachers(institutionTeachers); //For return
		
		Set<SchoolTeacher> schoolTeachers = new HashSet<>();
		Set<SaveSchoolTeacherDto> saveSchoolTeacherDtos = dto.getSaveSchoolTeacherDtos();
		for (SaveSchoolTeacherDto saveSchoolTeacherDto : saveSchoolTeacherDtos) {
			saveSchoolTeacherDto.setTutorId(tutor.getId());
			SchoolTeacher schoolTeacher = iSchoolTeacherService.create(saveSchoolTeacherDto);          // Create	
			schoolTeachers.add(schoolTeacher); //For return
		}
		tutor.setSchoolTeachers(schoolTeachers); //For return
		
		return tutor;
	}

	
	@Override
	public Tutor update(SaveTutorDto dto) {
		Tutor tutor = iTutorRepository.getOne(dto.getId());

		tutor = save(dto, tutor);
		
		//DELETE/CREATE/UPDATE Ở CÁC TABLE CÓ THAM CHIẾU TỚI MODEL
		for (Student student : tutor.getStudents()) {						      
			Boolean deleteThis = true;	
			for (SaveStudentDto saveStudentDto : dto.getSaveStudentDtos()) {
				if (student.getId() == saveStudentDto.getId())
					deleteThis = false; 
			}
			if (deleteThis) 
				iStudentService.delete(student.getId());                          // Delete
		}
		Set<Student> students = new HashSet<>(); //For return
		for (SaveStudentDto saveStudentDto : dto.getSaveStudentDtos()) {
			if (saveStudentDto.getId() == 0 || saveStudentDto.getId() == null) {  	  		  
				saveStudentDto.setTutorId(tutor.getId());
				Student student = iStudentService.create(saveStudentDto);         // Create	
				students.add(student); //For return
			} else { 														      
				Student student = iStudentService.update(saveStudentDto);         // Update
				students.add(student); //For return
			}
		}
		tutor.setStudents(students); //For return
		
		for (GraduatedStudent graduatedStudent : tutor.getGraduatedStudents()) {						      
			Boolean deleteThis = true;	
			for (SaveGraduatedStudentDto saveGraduatedStudentDto : dto.getSaveGraduatedStudentDtos()) {
				if (graduatedStudent.getId() == saveGraduatedStudentDto.getId())
					deleteThis = false; 
			}
			if (deleteThis) 
				iGraduatedStudentService.delete(graduatedStudent.getId());                          // Delete
		}
		Set<GraduatedStudent> graduatedStudents = new HashSet<>(); //For return
		for (SaveGraduatedStudentDto saveGraduatedStudentDto : dto.getSaveGraduatedStudentDtos()) {
			if (saveGraduatedStudentDto.getId() == 0 || saveGraduatedStudentDto.getId() == null) {  	  		  
				saveGraduatedStudentDto.setTutorId(tutor.getId());
				GraduatedStudent graduatedStudent = iGraduatedStudentService.create(saveGraduatedStudentDto);         // Create	
				graduatedStudents.add(graduatedStudent); //For return
			} else { 														      
				GraduatedStudent graduatedStudent = iGraduatedStudentService.update(saveGraduatedStudentDto);         // Update
				graduatedStudents.add(graduatedStudent); //For return
			}
		}
		tutor.setGraduatedStudents(graduatedStudents); //For return
		
		for (InstitutionTeacher institutionTeacher : tutor.getInstitutionTeachers()) {						      
			Boolean deleteThis = true;	
			for (SaveInstitutionTeacherDto saveInstitutionTeacherDto : dto.getSaveInstitutionTeacherDtos()) {
				if (institutionTeacher.getId() == saveInstitutionTeacherDto.getId())
					deleteThis = false; 
			}
			if (deleteThis) 
				iInstitutionTeacherService.delete(institutionTeacher.getId());                          // Delete
		}
		Set<InstitutionTeacher> institutionTeachers = new HashSet<>(); //For return
		for (SaveInstitutionTeacherDto saveInstitutionTeacherDto : dto.getSaveInstitutionTeacherDtos()) {
			if (saveInstitutionTeacherDto.getId() == 0 || saveInstitutionTeacherDto.getId() == null) {  	  		  
				saveInstitutionTeacherDto.setTutorId(tutor.getId());
				InstitutionTeacher institutionTeacher = iInstitutionTeacherService.create(saveInstitutionTeacherDto);         // Create	
				institutionTeachers.add(institutionTeacher); //For return
			} else { 														      
				InstitutionTeacher institutionTeacher = iInstitutionTeacherService.update(saveInstitutionTeacherDto);         // Update
				institutionTeachers.add(institutionTeacher); //For return
			}
		}
		tutor.setInstitutionTeachers(institutionTeachers); //For return
		
		for (SchoolTeacher schoolTeacher : tutor.getSchoolTeachers()) {						      
			Boolean deleteThis = true;	
			for (SaveSchoolTeacherDto saveSchoolTeacherDto : dto.getSaveSchoolTeacherDtos()) {
				if (schoolTeacher.getId() == saveSchoolTeacherDto.getId())
					deleteThis = false; 
			}
			if (deleteThis) 
				iSchoolTeacherService.delete(schoolTeacher.getId());                          // Delete
		}
		Set<SchoolTeacher> schoolTeachers = new HashSet<>(); //For return
		for (SaveSchoolTeacherDto saveSchoolTeacherDto : dto.getSaveSchoolTeacherDtos()) {
			if (saveSchoolTeacherDto.getId() == 0 || saveSchoolTeacherDto.getId() == null) {  	  		  
				saveSchoolTeacherDto.setTutorId(tutor.getId());
				SchoolTeacher schoolTeacher = iSchoolTeacherService.create(saveSchoolTeacherDto);         // Create	
				schoolTeachers.add(schoolTeacher); //For return
			} else { 														      
				SchoolTeacher schoolTeacher = iSchoolTeacherService.update(saveSchoolTeacherDto);         // Update
				schoolTeachers.add(schoolTeacher); //For return
			}
		}
		tutor.setSchoolTeachers(schoolTeachers); //For return
		
		
		return tutor;
	}

	
	@Override
	public Tutor save(SaveTutorDto dto, Tutor tutor) {
		try {
			// MAP DTO TO MODEL
			tutor = (Tutor) mapDtoToModel.map(dto, tutor);

			tutor.setTempArea(iAreaRepository.getOne(dto.getTempAreaId())); // Khi dùng getOne thì ko cần xét có tìm
																			// thấy hay ko, vì nếu ko tìm thấy thì nó ko
																			// thực hiện tiếp và throw Exception.

			tutor.setPerArea(iAreaRepository.getOne(dto.getPerAreaId()));

			tutor.setRelArea(iAreaRepository.getOne(dto.getRelAreaId()));
			
			// SAVE MOTDEL TO DB
			return iTutorRepository.save(tutor);

		} catch (Exception e) {e.printStackTrace();}

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

		} catch (Exception e) {e.printStackTrace();}
		
		return;

	}
	
	
	

}
