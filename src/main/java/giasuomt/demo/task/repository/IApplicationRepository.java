package giasuomt.demo.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.task.model.Application;

@Repository
public interface IApplicationRepository extends JpaRepository<Application, String> {
	
	@EntityGraph(value = "application")
	List<Application> findAll();
}
