package giasuomt.demo.tutorReview.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.job.model.Job;
import giasuomt.demo.person.model.Tutor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tutor_review")
@Getter
@Setter
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class TutorReview extends AbstractEntity {
 
	
	
	private Double starNumber;
	
	private String feedbackContent;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Tutor tutor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Job job;
	
	private String jobCreatedByCreatingTutorReview;
	
	@ElementCollection
	private Set<String>  publicFeedbackImgs=new HashSet<>();
	
	@ElementCollection
	private Set<String>  privateFeedbackImgs=new HashSet<>();
	
	
}	
