package giasuomt.demo.tutorReview.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TutorReviewDtoForWeb {
	private Double starNumber;
	
	private String feedbackContent;
	
	
	private String jobCreatedByCreatingTutorReview;
	
	@ElementCollection
	private Set<String>  publicFeedbackImgs=new HashSet<>();
	
	@ElementCollection
	private Set<String>  privateFeedbackImgs=new HashSet<>();
}
