package giasuomt.demo.tutorReview.dto;

import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import giasuomt.demo.job.model.Job;
import giasuomt.demo.person.model.Tutor;
import lombok.Getter;

@Getter
@Setter
public class SaveTutorReviewDto {
	
	private Long id;
	
	private Double starNumber;

	private String feedbackContent;

	private Long tutorId;

	private Long jobId;

}
