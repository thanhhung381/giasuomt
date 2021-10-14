package giasuomt.demo.educational.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import giasuomt.demo.educational.model.SubjectGroup;

public interface ISubjectGroupRepository extends JpaRepository<SubjectGroup, Long> {
	Optional<SubjectGroup> findById(Long id);
}
