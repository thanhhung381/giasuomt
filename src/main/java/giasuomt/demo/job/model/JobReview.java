package giasuomt.demo.job.model;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import giasuomt.demo.commondata.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "job_review")
@Getter
@Setter
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class JobReview extends AbstractEntity {
	
	private String starsNumber;
	
	private String feedback;
	
	@ElementCollection
	private List<String> feedbackImgs=new LinkedList<>();
	
	@ManyToOne
	@JoinColumn(name = "job_id")
	@JsonIgnore
	private Job job;
}
