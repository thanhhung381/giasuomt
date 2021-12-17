package giasuomt.demo.person.Ultils;

import java.util.List;

import giasuomt.demo.job.dto.SaveJobReviewDto;
import giasuomt.demo.job.model.Job;
import giasuomt.demo.person.model.Tutor;

public class ExperienceForTutor {

	public static Tutor updateExpForTutor(Tutor tutor) {

		Double countExp = 0.0;

		List<Job> allJobs = tutor.getJobs();
		for (int i = 0; i < allJobs.size(); i++) {
			if (allJobs.get(i).getJobResult().contains("success")) {
				countExp += 1.0;
			}
			if (allJobs.get(i).getFailReason().contains("PH/HV chê")
					|| allJobs.get(i).getFailReason().contains("do lỗi GS")) {
				countExp -= 1;
			}
			if (allJobs.get(i).getJobReviews() != null) {

				if (allJobs.get(i).getJobReviews().getStarsNumber() >= 4.0
						&& allJobs.get(i).getJobReviews().getStarsNumber() < 5.0) {
					countExp += 1.0;
				} else if (allJobs.get(i).getJobReviews().getStarsNumber() == 5.0) {
					countExp += 2.0;
				} else if (allJobs.get(i).getJobReviews().getStarsNumber() <= 2
						&& allJobs.get(i).getJobReviews().getStarsNumber() >= 0) {
					countExp -= 1;
				}
			}
		}

		tutor.setExp(countExp);
		
		return tutor;
	}
}
