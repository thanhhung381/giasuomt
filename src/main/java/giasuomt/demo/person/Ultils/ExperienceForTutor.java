package giasuomt.demo.person.Ultils;

import java.util.Set;

import giasuomt.demo.job.model.Job;
import giasuomt.demo.job.model.JobResult;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.tutorReview.model.TutorReview;

public class ExperienceForTutor {

	public static Tutor updateExpForTutor(Tutor tutor) {
		Double countExp = 0.0;
		Set<Job> allJobss = tutor.getJobs();
		for (Job allJobs : allJobss) {
			if (allJobs.getJobResult().equals(JobResult.SUCCESS)) {
				countExp += 1.0;
			}
			if (allJobs.getFailReason().contains("PH/HV chê")
					|| allJobs.getFailReason().contains("do lỗi GS")) {
				countExp -= 1;
			}
			if (allJobs.getTutorReviews() != null) {

				for (TutorReview tutorReview : allJobs.getTutorReviews()) {
					if (tutorReview.getStarNumber() >= 4.0
							&& tutorReview.getStarNumber() < 5.0) {
						countExp += 1.0;
					} else if (tutorReview.getStarNumber() == 5.0) {
						countExp += 2.0;
					} else if (tutorReview.getStarNumber() <= 2
							&& tutorReview.getStarNumber() >= 0) {
						countExp -= 1;
					}					
				}
			}
		}
		tutor.setExp(countExp);
		return tutor;
	}
}
