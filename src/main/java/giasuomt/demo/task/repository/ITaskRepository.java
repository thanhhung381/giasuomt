package giasuomt.demo.task.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.task.model.Task;

@Repository
public interface ITaskRepository extends JpaRepository<Task, String> {

	
//	@Query("SELECT MAX(id) FROM Task")
//	Long getMaxId();


	//int countById(Long id);
	
//	int countByTaskCode(String taskCode);
	
//	Task findByTaskCode(String taskCode);

	@Query("SELECT t FROM Task t WHERE t.id=:id")
	Optional<Task> findByIdString(String id);
	
////	@Query("SELECT t.registers FROM Task t WHERE t.id=id")
//	List<RegisterAndLearner> findRegistersById(Long id);
//
////	@Query("SELECT t.learners FROM Task t WHERE t.id=id")
//	List<RegisterAndLearner> findLearnersById(Long id);

}
