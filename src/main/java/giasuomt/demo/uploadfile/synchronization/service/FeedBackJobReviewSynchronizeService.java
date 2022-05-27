package giasuomt.demo.uploadfile.synchronization.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import giasuomt.demo.job.model.Job;
import giasuomt.demo.job.model.JobReview;
import giasuomt.demo.job.repository.IJobRepository;
import giasuomt.demo.job.repository.IJobReviewRepository;
import giasuomt.demo.uploadfile.service.IFeedBackImageAwsService;
import giasuomt.demo.uploadfile.service.IRetainedImgsIdentificationAwsService;

@Service
public class FeedBackJobReviewSynchronizeService implements IFeedBackJobReviewSynchronizeService {

	@Autowired
	private IFeedBackImageAwsService iFeedBackImageAwsService;
	
	
	@Autowired
	private IJobReviewRepository iJobReviewRepository ;
	
	@Override
	public Set<JobReview> findAllFeedBackJobReviewSynchronized() {
		// Avatar
		Set<String> urlFeedbackOfJobReview = Sets.newHashSet(iFeedBackImageAwsService.findAll());

		Set<JobReview> jobReviews = Sets.newHashSet(iJobReviewRepository.findAll());

		for (JobReview jobReview : jobReviews) {
	
			// privateImgs
			Set<String> synchronizeFeedbackImgs = jobReview.getFeedbackImgs();
			synchronizeFeedbackImgs .clear();
			for (String url : urlFeedbackOfJobReview) {
				boolean check = true;
				
				if (!url.contains(String.valueOf(jobReview.getId())+"Review")) {
					check = false;
				}

				if (check) {

					synchronizeFeedbackImgs.add(url);

					jobReview.setFeedbackImgs(synchronizeFeedbackImgs);

				}
			}

	

			iJobReviewRepository.save(jobReview);

		}

		return Sets.newHashSet(iJobReviewRepository.findAll());
	}

}
