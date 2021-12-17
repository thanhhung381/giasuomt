package giasuomt.demo.person.Ultils;

import java.util.List;

import giasuomt.demo.job.dto.SaveJobReviewDto;
import giasuomt.demo.job.model.Job;
import giasuomt.demo.person.model.Tutor;

public class ExperienceForTutor {

	public static void updateExpForTutor(Tutor tutor, SaveJobReviewDto dto) {
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
		}

		if (dto != null) {
			if (dto.getStarsNumber() >= 4.0 && dto.getStarsNumber() < 5.0) {
				countExp += 1.0;
			} else if (dto.getStarsNumber() == 5.0) {
				countExp += 2.0;
			} else if (dto.getStarsNumber() <= 2 && dto.getStarsNumber()>= 0 ) {
				countExp -= 1;
			}
		}

		Double totalPointExp = tutor.getExp();

		totalPointExp += countExp;

		tutor.setExp(totalPointExp);
	}
}
