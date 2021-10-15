package giasuomt.demo.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.task.model.Task;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {

}
