package giasuomt.demo.task.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
	Task findByIdString(String id);
	
////	@Query("SELECT t.registers FROM Task t WHERE t.id=id")
//	List<RegisterAndLearner> findRegistersById(Long id);
//
////	@Query("SELECT t.learners FROM Task t WHERE t.id=id")
//	List<RegisterAndLearner> findLearnersById(Long id);
	
	@Query("SELECT t FROM Task t WHERE t.status='AVAILABLE_PUBLIC' OR t.status='AVAILABLE_UNPUBLIC'")
	List<Task> findByAvailableTaskList();
	
	@Query("SELECT t FROM Task t WHERE t.status='CLOSED_GO' OR t.status='CLOSED_GO_SUCCESS' OR t.status='CLOSED_GO_FAIL'"
			+ "OR t.status='CLOSED_FAIL' ")
	List<Task> findByUnavailableTaskList();
	
	@Query("SELECT t FROM Task t   WHERE  t.createdAt=(SELECT MAX(createdAt) FROM Task)")
	Task findByTaskLast();
	
}
