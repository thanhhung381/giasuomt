package giasuomt.demo.person.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.person.model.GraduatedStudent;
@Repository
public interface IGraduatedStudentRepository extends JpaRepository<GraduatedStudent, Long> {
	
}
