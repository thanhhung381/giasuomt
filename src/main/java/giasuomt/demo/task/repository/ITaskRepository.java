package giasuomt.demo.task.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.task.model.Task;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {

	@Query("SELECT t.taskCode FROM Task t WHERE t.id=(SELECT MAX(id) FROM Task)")
	String getTaskCodeEndOfDay();

	@Query("SELECT MAX(id) FROM Task")
	Long getMaxId();


	int countById(Long id);
	
	int countByTaskCode(String taskCode);
	
	Task findByTaskCode(String taskCode);

	
////	@Query("SELECT t.registers FROM Task t WHERE t.id=id")
//	List<RegisterAndLearner> findRegistersById(Long id);
//
////	@Query("SELECT t.learners FROM Task t WHERE t.id=id")
//	List<RegisterAndLearner> findLearnersById(Long id);

}
