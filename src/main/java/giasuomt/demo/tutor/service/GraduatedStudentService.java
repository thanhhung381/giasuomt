package giasuomt.demo.tutor.service;
import java.util.List;
import org.springframework.stereotype.Service;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.tutor.dto.SaveGraduatedStudentDto;
import giasuomt.demo.tutor.model.GraduatedStudent;
import giasuomt.demo.tutor.repository.IGraduatedStudentRepository;
import giasuomt.demo.tutor.repository.ITutorRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GraduatedStudentService extends GenericService<GraduatedStudent, Long> implements IGraduatedStudentService {

	private MapDtoToModel mapper;

	// Repository
	private IGraduatedStudentRepository iGraduatedStudentRepository;

	private ITutorRepository iTutorRepository;

	@Override
	public List<GraduatedStudent> findAll() {

		return iGraduatedStudentRepository.findAll();
	}

	@Override
	public GraduatedStudent create(SaveGraduatedStudentDto dto) {
		GraduatedStudent graduatedStudent = new GraduatedStudent();

		graduatedStudent.setTutor(iTutorRepository.getOne(dto.getTutorId()));

		return save(dto, graduatedStudent);
	}

	@Override
	public GraduatedStudent update(SaveGraduatedStudentDto dto) {
		GraduatedStudent graduatedStudent = iGraduatedStudentRepository.getOne(dto.getId());

		return save(dto, graduatedStudent);
	}

	@Override
	public GraduatedStudent save(SaveGraduatedStudentDto dto, GraduatedStudent graduatedStudent) {

		try {
			// MAP DTO TO MODEL
			graduatedStudent = (GraduatedStudent) mapper.map(dto, graduatedStudent);

			// CREATE/UPDATE XUỐNG DB
			return iGraduatedStudentRepository.save(graduatedStudent);

		} catch (Exception e) {e.printStackTrace();}

		return null;

	}

	@Override
	public void delete(Long id) {
		try {
			
			iGraduatedStudentRepository.deleteById(id);
			
		} catch (Exception e) {e.printStackTrace();}
		
		return;
	}


}
