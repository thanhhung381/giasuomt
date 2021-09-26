package giasuomt.demo.learnerAndRegister.service;
import java.util.List;
import java.util.Set;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.learnerAndRegister.dto.SaveLearnerAndRegisterDTO_Staff;
import giasuomt.demo.learnerAndRegister.dto.SaveLearnerAndRegistersDTO_Staff;
import giasuomt.demo.learnerAndRegister.model.LearnerAndRegister;

public interface ILearnerAndRegisterService extends IGenericService<LearnerAndRegister, Long>{
	public Set<LearnerAndRegister> saveLearnerAndRegisters(SaveLearnerAndRegistersDTO_Staff dto);
	
	public LearnerAndRegister saveLearnerAndRegister(SaveLearnerAndRegisterDTO_Staff learnerAndRegisterDto, LearnerAndRegister addedLearnerAndRegister);

	public List<LearnerAndRegister> findByEmail(String email);
	
	public List<LearnerAndRegister> findByFullName(String fullName);

	public List<LearnerAndRegister> findByFullNameAnd(String fullNameAnd);
}
