package giasuomt.demo.job.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveJobReviewDto {
	private Long id;
	private Double starsNumber;
	private String feedback;
	private String jobId;
}
