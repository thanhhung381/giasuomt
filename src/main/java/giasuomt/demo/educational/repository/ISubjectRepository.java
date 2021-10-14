package giasuomt.demo.educational.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.educational.model.Subject;

@Repository
public interface ISubjectRepository extends JpaRepository<Subject, Long> {

	int countById(Long id);
}
