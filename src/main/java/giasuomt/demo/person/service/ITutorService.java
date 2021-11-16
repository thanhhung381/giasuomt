package giasuomt.demo.person.service;
import java.util.List;
import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.person.dto.SaveTutorDto;
import giasuomt.demo.person.model.Tutor;

public interface ITutorService extends IGenericService<SaveTutorDto, Tutor, Long> {

	public Tutor create(SaveTutorDto dto);

	public Tutor update(SaveTutorDto dto);

	public Tutor save(SaveTutorDto dto, Tutor tutor);

	public void delete(Long id);

	public Tutor findByTutorCode(String tutorCode);

	

	public List<Tutor> findByPhoneNumber(String phoneNumber);

	public List<Tutor> findByEndPhoneNumber(String endPhoneNumber);



	public List<Tutor> findByFullNameContain(String fullName);

}
