package giasuomt.demo.person.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.person.model.Student;
@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {
	

	
	//
	//@Override
	//@EntityGraph(attributePaths="tutor.students",type=EntityGraphType.FETCH)
	//public  List<Student> findAll();
	
	
	
}
