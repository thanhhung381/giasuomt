package giasuomt.demo.educational.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.educational.model.SubjectGroup;
@Repository
public interface ISubjectGroupRepository extends JpaRepository<SubjectGroup, String> {
	//Optional<SubjectGroup> findById(String id);
}
