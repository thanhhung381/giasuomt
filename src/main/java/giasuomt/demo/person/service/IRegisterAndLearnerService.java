package giasuomt.demo.person.service;
import java.util.List;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.person.dto.SaveRegisterAndLearnerDto;
import giasuomt.demo.person.model.RegisterAndLearner;

public interface IRegisterAndLearnerService extends IGenericService<SaveRegisterAndLearnerDto, RegisterAndLearner, Long> {
	public RegisterAndLearner create(SaveRegisterAndLearnerDto dto);

	public RegisterAndLearner update(SaveRegisterAndLearnerDto dto);

	public RegisterAndLearner save(SaveRegisterAndLearnerDto dto, RegisterAndLearner registerAndLearner);

	public void delete(Long id);

	List<RegisterAndLearner> findByPhoneNumber(String phoneNumber);

	List<RegisterAndLearner> findByEndPhoneNumber(String endPhoneNumber);


}
