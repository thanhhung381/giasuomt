package giasuomt.demo.job.dto;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.ElementCollection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveJobReviewDto {
	
	
	private Long id;
	
	private String starsNumber;
	
	private String feedback;
	
	private Long jobId;
	
	private List<Long> feedbackImgIds=new LinkedList<>();
}
