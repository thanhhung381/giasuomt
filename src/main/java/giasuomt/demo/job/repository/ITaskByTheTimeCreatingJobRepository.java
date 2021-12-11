package giasuomt.demo.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.job.model.TaskByTheTimeCreatingJob;

@Repository
public interface ITaskByTheTimeCreatingJobRepository extends JpaRepository<TaskByTheTimeCreatingJob, String> {

}
