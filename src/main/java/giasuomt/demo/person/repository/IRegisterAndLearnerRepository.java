package giasuomt.demo.person.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.person.model.Tutor;

@Repository
public interface IRegisterAndLearnerRepository extends JpaRepository<RegisterAndLearner, Long> {
	
}
