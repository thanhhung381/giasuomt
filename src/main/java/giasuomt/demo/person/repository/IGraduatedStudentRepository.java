package giasuomt.demo.person.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import giasuomt.demo.person.model.GraduatedStudent;
@Repository
public interface IGraduatedStudentRepository extends JpaRepository<GraduatedStudent, Long> {
	@Query("SELECT s.id FROM GraduatedStudent s WHERE s.tutor.id=:id")
	Set<Long> findGraduatedStudentIdByTutorId(@Param("id") Long id);
}
