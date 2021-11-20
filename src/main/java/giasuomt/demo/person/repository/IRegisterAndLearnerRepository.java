package giasuomt.demo.person.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.person.model.Tutor;

@Repository
public interface IRegisterAndLearnerRepository extends JpaRepository<RegisterAndLearner, Long> {
	
	List<RegisterAndLearner> findByPhonesContaining(String phones);
}
