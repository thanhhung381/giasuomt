package giasuomt.demo.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.job.model.JobReview;

@Repository
public interface IJobReviewRepository extends JpaRepository<JobReview, Long> {

}
