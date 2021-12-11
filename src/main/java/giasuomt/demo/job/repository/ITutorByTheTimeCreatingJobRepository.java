package giasuomt.demo.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.job.model.TutorByTheTimeCreatingJob;

@Repository
public interface ITutorByTheTimeCreatingJobRepository extends JpaRepository<TutorByTheTimeCreatingJob, Long> {

}
