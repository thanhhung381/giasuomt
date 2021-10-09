package giasuomt.demo.tutor.service;
import java.util.Optional;
import org.springframework.stereotype.Service;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.tutor.dto.SaveSchoolTeacherDto;
import giasuomt.demo.tutor.model.SchoolTeacher;
import giasuomt.demo.tutor.model.Tutor;
import giasuomt.demo.tutor.repository.ISchoolTeacherRepository;
import giasuomt.demo.tutor.repository.ITutorRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SchoolTeacherService extends GenericService<SchoolTeacher, Long> implements ISchoolTeacherService {

	private ISchoolTeacherRepository schoolTeacherRepository;

	private ITutorRepository tutorRepository;

	private MapDtoToModel mapper;

	@Override
	public SchoolTeacher save(SaveSchoolTeacherDto dto) {

		try {
			SchoolTeacher schoolTeacher = new SchoolTeacher();
			schoolTeacher = (SchoolTeacher) mapper.map(dto, schoolTeacher);
			Optional<Tutor> tutor = Optional.ofNullable(tutorRepository.getOne(dto.getTutorId()));
			if (tutor.isPresent())
				schoolTeacher.setTutor(tutor.get());

			return super.save(schoolTeacher);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}

}
