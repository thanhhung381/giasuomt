package giasuomt.demo.person.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.person.dto.SaveRegisterAndLearnerDto;
import giasuomt.demo.person.dto.UpdateAvatarRegisterAndLearner;
import giasuomt.demo.person.model.RegisterAndLearner;

public interface IRegisterAndLearnerService
		extends IGenericService<SaveRegisterAndLearnerDto, RegisterAndLearner, Long> {
	public RegisterAndLearner create(SaveRegisterAndLearnerDto dto);

	public RegisterAndLearner update(SaveRegisterAndLearnerDto dto);

	public RegisterAndLearner save(SaveRegisterAndLearnerDto dto, RegisterAndLearner registerAndLearner);

	public void delete(Long id);

	List<RegisterAndLearner> findByPhoneNumber(String phoneNumber);

	List<RegisterAndLearner> findByEndPhoneNumber(String endPhoneNumber);

	List<RegisterAndLearner> findByFullNameContaining(String fullName);

	List<RegisterAndLearner> findByEnglishFullNameContaining(String englishFullName);

	List<String> findByEnglishNameAndShowEngLishFullName(String englishFullName);

	List<String> findByFullNameAndShowFullName(String fullName);

	List<RegisterAndLearner> findByVocativeAndFullName(String vocative, String fullName);

	List<String> findByVocativeAndFullNameAndShowFullName(String vocative, String fullName);

	List<RegisterAndLearner> findByVocativeAndEnglishFullNameContaining(String vocative, String englishName);

	List<String> findByVocativeAndEnglishFullNameAndShowFullName(String vocative, String englishName);

	 RegisterAndLearner updateAvatarRegisterAndLearner(UpdateAvatarRegisterAndLearner dto);

}
