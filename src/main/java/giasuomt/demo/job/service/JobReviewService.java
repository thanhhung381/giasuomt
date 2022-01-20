package giasuomt.demo.job.service;

import java.util.LinkedList;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.job.dto.SaveJobReviewDto;
import giasuomt.demo.job.model.Job;
import giasuomt.demo.job.model.JobReview;
import giasuomt.demo.job.repository.IJobRepository;
import giasuomt.demo.job.repository.IJobReviewRepository;
import giasuomt.demo.person.Ultils.ExperienceForTutor;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.person.repository.ITutorRepository;
import giasuomt.demo.uploadfile.model.FeedbackImageAws;
import giasuomt.demo.uploadfile.repository.IFeedbackImageAwsRepository;
import giasuomt.demo.uploadfile.service.IFeedBackImageAwsService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JobReviewService extends GenericService<SaveJobReviewDto, JobReview, Long> implements IJobReviewService {

	private IJobReviewRepository iJobReviewRepository;

	private IFeedbackImageAwsRepository iFeedBackImageRepository;

	private IJobRepository iJobRepository;

	private ITutorRepository iTutorRepository;

	private MapDtoToModel mapDtoToModel;

	public JobReview create(SaveJobReviewDto dto) {

		JobReview jobReview = new JobReview();

		jobReview.setJob(iJobRepository.getOne(dto.getJobId()));

		return save(dto, jobReview);
	}

	void map(SaveJobReviewDto dto, JobReview jobReview) {
		jobReview = (JobReview) mapDtoToModel.map(dto, jobReview);

		List<Long> feedbackImagesId = dto.getFeedbackImgIds();

		List<String> feedbacks = new LinkedList<>();

		for (int i = 0; i < feedbackImagesId.size(); i++) {
			FeedbackImageAws feedBackImage = iFeedBackImageRepository.getOne(feedbackImagesId.get(i));

			feedbacks.add(feedBackImage.getUrlFeedbackImage());

		}
		jobReview.setFeedbackImgs(feedbacks);

	}

	public JobReview save(SaveJobReviewDto dto, JobReview jobReview) {
		try {

			map(dto, jobReview);

			return iJobReviewRepository.save(jobReview);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JobReview update(SaveJobReviewDto dto) {

		JobReview jobReview = iJobReviewRepository.getOne(dto.getId());

		jobReview.setJob(iJobRepository.getOne(dto.getJobId()));

		jobReview = save(dto, jobReview);

		Tutor tutor = iTutorRepository.getOne(jobReview.getJob().getTutor().getId());

		tutor = ExperienceForTutor.updateExpForTutor(tutor);

		iTutorRepository.save(tutor);

		return jobReview;
	}

}
