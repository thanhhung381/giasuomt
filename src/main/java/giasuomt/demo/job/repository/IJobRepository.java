package giasuomt.demo.job.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import giasuomt.demo.job.model.Job;

@Repository
public interface IJobRepository extends JpaRepository<Job, String> {

	@EntityGraph("job")
	List<Job> findAll();
}
