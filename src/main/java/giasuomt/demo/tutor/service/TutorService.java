package giasuomt.demo.tutor.service;
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

	// Repository

	private IAreaRepository iAreaRepository;

	private IStudentRepository iStudentRepository;

	private ISchoolTeacherRepository iSchoolTeacherRepository;

	private IInstitutionTeacherRepository iInstitutionTeacherRepository;

	private IGraduatedStudentRepository iGraduatedStudentRepository;

	
	@Override
	public List<Tutor> findAll() {
		return iTutorRepository.findAll();
	}

	
	@Override
	public Tutor create(SaveTutorDto dto) {
		Tutor tutor = new Tutor();
		
		tutor.setTutorCode("new tutor code");
			
		return save(dto, tutor);
	}

	@Override
	public Tutor update(SaveTutorDto dto) {
		
		Tutor tutor = iTutorRepository.getOne(dto.getId());
		
		return save(dto, tutor);
	}
	
	
	@Override
	public Tutor save(SaveTutorDto dto, Tutor tutor) {
		try {	
			tutor = (Tutor) mapDtoToModel.map(dto, tutor);

			tutor.setTempArea(iAreaRepository.getOne(dto.getTempAreaId())); 

			tutor.setPerArea(iAreaRepository.getOne(dto.getPerAreaId()));

			tutor.setRelArea(iAreaRepository.getOne(dto.getRelAreaId()));
			
			List<SaveStudentDto> saveStudentDtos = dto.getSaveStudentDtos();
			for(int i = 0; i < tutor.getStudents().size(); i++) {
				Boolean deleteThis = true;
				for(int j = 0; j < saveStudentDtos.size(); j++) {
					if(tutor.getStudents().get(i).getId() == saveStudentDtos.get(j).getId())
						deleteThis = false;
				}
				if(deleteThis) {
					tutor.removeStudent(tutor.getStudents().get(i));                      //Delete 
					i--; //Vì nó đã remove 1 element trong array lên phải trừ đi
				}	                 			
			}
			for(int i = 0; i < saveStudentDtos.size(); i++) {
				SaveStudentDto saveStudentDto = saveStudentDtos.get(i);
				if(saveStudentDto.getId() != null && saveStudentDto.getId() > 0) {       //Update
					Student student = iStudentRepository.getOne(saveStudentDto.getId());
					student = (Student) mapDtoToModel.map(saveStudentDto, student);
					tutor.addStudent(student);					
				}
				else {																	 //Create
					Student student = new Student();
					student = (Student) mapDtoToModel.map(saveStudentDto, student);
					tutor.addStudent(student);	
				}
			}
			
			List<SaveGraduatedStudentDto> saveGraduatedStudentDtos = dto.getSaveGraduatedStudentDtos();
			for(int i = 0; i < tutor.getGraduatedStudents().size(); i++) {
				Boolean deleteThis = true;
				for(int j = 0; j < saveGraduatedStudentDtos.size(); j++) {
					if(tutor.getGraduatedStudents().get(i).getId() == saveGraduatedStudentDtos.get(j).getId())
						deleteThis = false;
				}
				if(deleteThis) {
					tutor.removeGraduatedStudent(tutor.getGraduatedStudents().get(i));                      //Delete 
					i--; //Vì nó đã remove 1 element trong array lên phải trừ đi
				}	                 			
			}
			for(int i = 0; i < saveGraduatedStudentDtos.size(); i++) {
				SaveGraduatedStudentDto saveGraduatedStudentDto = saveGraduatedStudentDtos.get(i);
				if(saveGraduatedStudentDto.getId() != null && saveGraduatedStudentDto.getId() > 0) {       //Update
					GraduatedStudent graduatedStudent = iGraduatedStudentRepository.getOne(saveGraduatedStudentDto.getId());
					graduatedStudent = (GraduatedStudent) mapDtoToModel.map(saveGraduatedStudentDto, graduatedStudent);
					tutor.addGraduatedStudent(graduatedStudent);					
				}
				else {																	 //Create
					GraduatedStudent graduatedStudent = new GraduatedStudent();
					graduatedStudent = (GraduatedStudent) mapDtoToModel.map(saveGraduatedStudentDto, graduatedStudent);
					tutor.addGraduatedStudent(graduatedStudent);	
				}
			}
			
			List<SaveInstitutionTeacherDto> saveInstitutionTeacherDtos = dto.getSaveInstitutionTeacherDtos();
			for(int i = 0; i < tutor.getInstitutionTeachers().size(); i++) {
				Boolean deleteThis = true;
				for(int j = 0; j < saveInstitutionTeacherDtos.size(); j++) {
					if(tutor.getInstitutionTeachers().get(i).getId() == saveInstitutionTeacherDtos.get(j).getId())
						deleteThis = false;
				}
				if(deleteThis) {
					tutor.removeInstitutionTeacher(tutor.getInstitutionTeachers().get(i));                      //Delete 
					i--; //Vì nó đã remove 1 element trong array lên phải trừ đi
				}	                 			
			}
			for(int i = 0; i < saveInstitutionTeacherDtos.size(); i++) {
				SaveInstitutionTeacherDto saveInstitutionTeacherDto = saveInstitutionTeacherDtos.get(i);
				if(saveInstitutionTeacherDto.getId() != null && saveInstitutionTeacherDto.getId() > 0) {       //Update
					InstitutionTeacher institutionTeacher = iInstitutionTeacherRepository.getOne(saveInstitutionTeacherDto.getId());
					institutionTeacher = (InstitutionTeacher) mapDtoToModel.map(saveInstitutionTeacherDto, institutionTeacher);
					tutor.addInstitutionTeacher(institutionTeacher);					
				}
				else {																	 //Create
					InstitutionTeacher institutionTeacher = new InstitutionTeacher();
					institutionTeacher = (InstitutionTeacher) mapDtoToModel.map(saveInstitutionTeacherDto, institutionTeacher);
					tutor.addInstitutionTeacher(institutionTeacher);	
				}
			}
			
			List<SaveSchoolTeacherDto> saveSchoolTeacherDtos = dto.getSaveSchoolTeacherDtos();
			for(int i = 0; i < tutor.getSchoolTeachers().size(); i++) {
				Boolean deleteThis = true;
				for(int j = 0; j < saveSchoolTeacherDtos.size(); j++) {
					if(tutor.getSchoolTeachers().get(i).getId() == saveSchoolTeacherDtos.get(j).getId())
						deleteThis = false;
				}
				if(deleteThis) {
					tutor.removeSchoolTeacher(tutor.getSchoolTeachers().get(i));                      //Delete 
					i--; //Vì nó đã remove 1 element trong array lên phải trừ đi
				}	                 			
			}
			for(int i = 0; i < saveSchoolTeacherDtos.size(); i++) {
				SaveSchoolTeacherDto saveSchoolTeacherDto = saveSchoolTeacherDtos.get(i);
				if(saveSchoolTeacherDto.getId() != null && saveSchoolTeacherDto.getId() > 0) {       //Update
					SchoolTeacher schoolTeacher = iSchoolTeacherRepository.getOne(saveSchoolTeacherDto.getId());
					schoolTeacher = (SchoolTeacher) mapDtoToModel.map(saveSchoolTeacherDto, schoolTeacher);
					tutor.addSchoolTeacher(schoolTeacher);					
				}
				else {																	 //Create
					SchoolTeacher schoolTeacher = new SchoolTeacher();
					schoolTeacher = (SchoolTeacher) mapDtoToModel.map(saveSchoolTeacherDto, schoolTeacher);
					tutor.addSchoolTeacher(schoolTeacher);	
				}
			}

			return iTutorRepository.save(tutor);
			
		} catch (Exception e) {e.printStackTrace();}

		return null;

	}
	
	@Override
	public void delete(Long id) {
		try {
			iTutorRepository.deleteById(id);
		} catch (Exception e) {e.printStackTrace();}
	}
	

}
