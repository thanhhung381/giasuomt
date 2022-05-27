package giasuomt.demo.uploadfile.synchronization.service;

import java.util.Set;

import giasuomt.demo.job.model.Job;
import giasuomt.demo.job.model.JobReview;

public interface IFeedBackJobReviewSynchronizeService {
	Set<JobReview> findAllFeedBackJobReviewSynchronized();
}
