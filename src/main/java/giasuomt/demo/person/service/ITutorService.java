package giasuomt.demo.person.service;
import java.util.List;
import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.person.dto.SaveTutorDto;
import giasuomt.demo.person.dto.UpdateRegisteredSubject;
import giasuomt.demo.person.dto.UpdateTutorAvatar;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.staff.dto.UpdateAvatarStaff;

public interface ITutorService extends IGenericService<SaveTutorDto, Tutor,Long> {

	public Tutor create(SaveTutorDto dto);

	public Tutor update(SaveTutorDto dto);

	public Tutor save(SaveTutorDto dto, Tutor tutor);

	public void delete(Long id);

	public Tutor findByTutorCode(Long tutorCode);

	public List<Tutor> findByPhoneNumber(String phoneNumber);

	public List<Tutor> findByEndPhoneNumber(String endPhoneNumber);

	public List<Tutor> findByFullNameContain(String fullName);
	
	public List<Tutor> findByEnglishFullName(String fullname);
	
	public List<String> findByfullnameAndShowFullName(String fullname);
	
	public List<String> findByEngfullnameAndShowFullName(String fullname); 
	
	public Tutor updateRegisteredSubjects(UpdateRegisteredSubject dto);

	public Tutor updateAvatarTutor(UpdateTutorAvatar dto);
}
