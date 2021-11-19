package giasuomt.demo.tags.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import giasuomt.demo.tags.model.TutorTag;

@Repository
public interface ITutorTagRepository extends JpaRepository<TutorTag, Long> {
	int countById(Long id);
}
