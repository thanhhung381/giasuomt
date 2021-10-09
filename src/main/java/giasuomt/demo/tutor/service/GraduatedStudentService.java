package giasuomt.demo.tutor.service;
import java.util.Optional;
import org.springframework.stereotype.Service;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.tutor.dto.SaveGraduatedStudentDto;
import giasuomt.demo.tutor.model.GraduatedStudent;
import giasuomt.demo.tutor.model.Tutor;
import giasuomt.demo.tutor.repository.IGraduatedStudentRepository;
import giasuomt.demo.tutor.repository.ITutorRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GraduatedStudentService extends GenericService<GraduatedStudent, Long>
		implements IGraduatedStudentService {
	// repository
	private IGraduatedStudentRepository iGraduatedStudentRepository;
	
	private ITutorRepository iTutorRepository;
	// mapper
	private MapDtoToModel mapper;

	

	@Override
	public GraduatedStudent save(SaveGraduatedStudentDto dto) {

		try {
			GraduatedStudent graduatedStudent = new GraduatedStudent();
			graduatedStudent = (GraduatedStudent) mapper.map(dto, graduatedStudent);

			Optional<Tutor> tutor = Optional.ofNullable(iTutorRepository.getOne(dto.getTutorId()));
			if (tutor.isPresent())
				graduatedStudent.setTutor(tutor.get());

			return super.save(graduatedStudent);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

}
