package giasuomt.demo.person.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import giasuomt.demo.person.model.Student;
@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {
	
	
	@Query("SELECT s FROM Student s WHERE s.tutor.tutorCode=:tutorCode ")
	Set<Student> findByallStudent(@Param("tutorCode") String tutorCode);
	
	@Query("SELECT s FROM Student s WHERE s.tutor.id=:id")
	Set<Student> findByStudentByIdTutors(@Param("id") Long id);

	@Query("SELECT s FROM Student s WHERE s.tutor.tutorCode=:code")
	Optional<Student> findByTutorCode(@Param("code") String code);
	
	
	@Query("SELECT s.id FROM Student s WHERE s.tutor.id=:id")
	Set<Long> findStudentIdByTutorId(@Param("id") Long id);
	
	//
	//@Override
	//@EntityGraph(attributePaths="tutor.students",type=EntityGraphType.FETCH)
	//public  List<Student> findAll();
	
	
	
}
