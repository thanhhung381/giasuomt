package giasuomt.demo.learnerAndRegister.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import giasuomt.demo.learnerAndRegister.model.LearnerAndRegister;

@Repository
public interface ILearnerAndRegisterRepository extends JpaRepository<LearnerAndRegister, Long> {//"Long" lớn khác "long" nhở ở chỗ: "Long" lớn là 1 lớp đối tượng
	
//	@Query("SELECT e FROM LearnerAndRegister e, IN (e.emails) t WHERE t LIKE %?1%")
//	@Query("SELECT e FROM LearnerAndRegister e JOIN e.emails t WHERE t LIKE %?1%")
	List<LearnerAndRegister> findByEmails(String email);

	List<LearnerAndRegister> findByFullNameContaining(String fullName);

	List<LearnerAndRegister> findByFullNameContainingAndBirthYearAndGenderAndAddNoAndAddSt(String fullNameAnd,
			String string1, String gender, String string3, String string4); 

}
