package giasuomt.demo.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import giasuomt.demo.task.model.Task;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {

	@Query("SELECT t.taskCode FROM Task t WHERE t.id=(SELECT MAX(id) FROM Task)")
	String getTaskCodeEndOfDay();

	@Query("SELECT MAX(id) FROM Task")
	Long getMaxId();

	

	int countById(Long id);

}
