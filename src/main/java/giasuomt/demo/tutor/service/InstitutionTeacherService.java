package giasuomt.demo.tutor.service;
import java.util.List;
import org.springframework.stereotype.Service;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.tutor.dto.SaveInstitutionTeacherDto;
import giasuomt.demo.tutor.model.InstitutionTeacher;
import giasuomt.demo.tutor.repository.IInstitutionTeacherRepository;
import giasuomt.demo.tutor.repository.ITutorRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InstitutionTeacherService extends GenericService<InstitutionTeacher, Long> implements IInstitutionTeacherService {

	private MapDtoToModel mapper;

	// Repository
	private IInstitutionTeacherRepository iInstitutionTeacherRepository;

	private ITutorRepository iTutorRepository;

	@Override
	public List<InstitutionTeacher> findAll() {

		return iInstitutionTeacherRepository.findAll();
	}

	@Override
	public InstitutionTeacher create(SaveInstitutionTeacherDto dto) {
		InstitutionTeacher institutionTeacher = new InstitutionTeacher();

		institutionTeacher.setTutor(iTutorRepository.getOne(dto.getId()));

		return save(dto, institutionTeacher);
	}

	@Override
	public InstitutionTeacher update(SaveInstitutionTeacherDto dto) {
		InstitutionTeacher institutionTeacher = iInstitutionTeacherRepository.getOne(dto.getId());

		return save(dto, institutionTeacher);
	}

	@Override
	public InstitutionTeacher save(SaveInstitutionTeacherDto dto, InstitutionTeacher institutionTeacher) {

		try {
			// MAP DTO TO MODEL
			institutionTeacher = (InstitutionTeacher) mapper.map(dto, institutionTeacher);

			// CREATE/UPDATE XUỐNG DB
			return iInstitutionTeacherRepository.save(institutionTeacher);

		} catch (Exception e) {e.printStackTrace();}

		return null;

	}

	@Override
	public void delete(Long id) {
		try {
			
			iInstitutionTeacherRepository.deleteById(id);
			
		} catch (Exception e) {e.printStackTrace();}
		
		return;
	}



}
