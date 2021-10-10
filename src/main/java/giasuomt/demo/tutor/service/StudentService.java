package giasuomt.demo.tutor.service;
import java.util.List;
import org.springframework.stereotype.Service;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.tutor.dto.SaveStudentDto;
import giasuomt.demo.tutor.model.Student;
import giasuomt.demo.tutor.repository.IStudentRepository;
import giasuomt.demo.tutor.repository.ITutorRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentService extends GenericService<Student, Long> implements IStudentService {

	private MapDtoToModel mapper;

	// Repository
	private IStudentRepository iStudentRepository;

	private ITutorRepository iTutorRepository;

	@Override
	public List<Student> findAll() {

		return iStudentRepository.findAll();
	}

	@Override
	public Student create(SaveStudentDto dto) {
		Student student = new Student();

//		student.setTutor(iTutorRepository.getOne(dto.getTutorId()));

		return save(dto, student);
	}

	@Override
	public Student update(SaveStudentDto dto) {
		Student student = iStudentRepository.getOne(dto.getId());

		return save(dto, student);
	}

	@Override
	public Student save(SaveStudentDto dto, Student student) {

		try {
			// MAP DTO TO MODEL
			student = (Student) mapper.map(dto, student);

			// CREATE/UPDATE XUỐNG DB
			return iStudentRepository.save(student);

		} catch (Exception e) {e.printStackTrace();}

		return null;

	}

	@Override
	public void delete(Long id) {
		try {
			
			iStudentRepository.deleteById(id);
			
		} catch (Exception e) {e.printStackTrace();}
		
		return;
	}

}
