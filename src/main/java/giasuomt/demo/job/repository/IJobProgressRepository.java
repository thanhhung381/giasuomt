package giasuomt.demo.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.job.model.JobProgress;

@Repository
public interface IJobProgressRepository extends JpaRepository<JobProgress, Long> {

}
