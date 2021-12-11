package giasuomt.demo.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import giasuomt.demo.job.model.Job;

@Repository
public interface IJobRepository extends JpaRepository<Job, Long> {

}
