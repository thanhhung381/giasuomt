package giasuomt.demo.tutorReview.service;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.job.repository.IJobRepository;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.person.repository.ITutorRepository;
import giasuomt.demo.tutorReview.dto.SaveTutorReviewDto;
import giasuomt.demo.tutorReview.model.TutorReview;
import giasuomt.demo.tutorReview.repository.ITutorReviewRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TutorReviewService extends GenericService<SaveTutorReviewDto, TutorReview, Long>
		implements ITutorReviewService {

	private ITutorReviewRepository iTutorReviewRepository;

	private ITutorRepository iTutorRepository;

	private IJobRepository iJobRepository;

	private MapDtoToModel mapDtoToModel;

	public TutorReview create(SaveTutorReviewDto dto) {

		TutorReview tutorReview = new TutorReview();
		tutorReview.setTutor(iTutorRepository.getOne(dto.getTutorId()));

		if (dto.getJobId() != 0) {
			tutorReview.setJob(iJobRepository.getOne(dto.getJobId()));
		}

		return save(tutorReview, dto);
	}

	@Override
	public TutorReview update(SaveTutorReviewDto dto) {

		TutorReview tutorReview = iTutorReviewRepository.getOne(dto.getId());

		return save(tutorReview, dto);
	}

	private TutorReview save(TutorReview tutorReview, SaveTutorReviewDto dto) {
		try {
			mapDto(tutorReview, dto);

			tutorReview = iTutorReviewRepository.save(tutorReview);// sau khi tạo or update

			Tutor tutor = iTutorRepository.getOne(dto.getTutorId());

			tutor.setAverageStarNumbers(updateAverageStarNumber(tutor));

			tutor = iTutorRepository.save(tutor);

			return tutorReview;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private Double updateAverageStarNumber(Tutor tutor) {
		List<TutorReview> starNumbersList = tutor.getTutorReviews();// lấy tất cả các
		// starNumber
		// của Tutor mún
		// thêm or
		// Update

			// tính toán

		Double sum = 0.0;

		for (int i = 0; i < starNumbersList.size(); i++) {
			sum += starNumbersList.get(i).getStarNumber();
		}

		Double resultAvarage = (sum / starNumbersList.size());

		DecimalFormat decimalFormat = new DecimalFormat("#.#");
		
		return Double.parseDouble(decimalFormat.format(resultAvarage));
	}

	private void mapDto(TutorReview tutorReview, SaveTutorReviewDto dto) {
		mapDtoToModel.map(dto, tutorReview);

	}

}
