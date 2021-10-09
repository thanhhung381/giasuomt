package giasuomt.demo.tutor.service;
import java.util.List;
import org.springframework.stereotype.Service;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.tutor.dto.SaveSchoolTeacherDto;
import giasuomt.demo.tutor.model.SchoolTeacher;
import giasuomt.demo.tutor.repository.ISchoolTeacherRepository;
import giasuomt.demo.tutor.repository.ITutorRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SchoolTeacherService extends GenericService<SchoolTeacher, Long> implements ISchoolTeacherService {

	private MapDtoToModel mapper;

	// Repository
	private ISchoolTeacherRepository iSchoolTeacherRepository;

	private ITutorRepository iTutorRepository;

	@Override
	public List<SchoolTeacher> findAll() {

		return iSchoolTeacherRepository.findAll();
	}

	@Override
	public SchoolTeacher create(SaveSchoolTeacherDto dto) {
		SchoolTeacher schoolTeacher = new SchoolTeacher();

		schoolTeacher.setTutor(iTutorRepository.getOne(dto.getTutorId()));

		return save(dto, schoolTeacher);
	}

	@Override
	public SchoolTeacher update(SaveSchoolTeacherDto dto) {
		SchoolTeacher schoolTeacher = iSchoolTeacherRepository.getOne(dto.getId());

		return save(dto, schoolTeacher);
	}

	@Override
	public SchoolTeacher save(SaveSchoolTeacherDto dto, SchoolTeacher schoolTeacher) {

		try {
			// MAP DTO TO MODEL
			schoolTeacher = (SchoolTeacher) mapper.map(dto, schoolTeacher);

			// CREATE/UPDATE XUỐNG DB
			return iSchoolTeacherRepository.save(schoolTeacher);

		} catch (Exception e) {e.printStackTrace();}

		return null;

	}

	@Override
	public void delete(Long id) {
		try {
			
			iSchoolTeacherRepository.deleteById(id);
			
		} catch (Exception e) {e.printStackTrace();}
		
		return;
	}

}
