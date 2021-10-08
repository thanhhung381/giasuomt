package giasuomt.demo.tutor.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import giasuomt.demo.tutor.model.SchoolTeacher;

@Repository
public interface SchoolTeacherRepository extends JpaRepository<SchoolTeacher, Long> {
	@Query("SELECT s.id FROM SchoolTeacher s WHERE s.tutor.id=:id")
	Set<Long> findSchoolTeacherIdByTutorId(@Param("id") Long id);
}
