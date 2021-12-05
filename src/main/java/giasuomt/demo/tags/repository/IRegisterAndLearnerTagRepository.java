package giasuomt.demo.tags.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import giasuomt.demo.tags.model.RegisterAndLearnerTag;

@Repository
public interface IRegisterAndLearnerTagRepository extends JpaRepository<RegisterAndLearnerTag, Long> {
	int countById(Long id);
}
