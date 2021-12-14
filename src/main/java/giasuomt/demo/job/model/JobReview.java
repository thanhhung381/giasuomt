package giasuomt.demo.job.model;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import giasuomt.demo.commondata.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "job_review")
@Getter
@Setter
public class JobReview extends AbstractEntity {
	private String starsNumber;
	
	private String feedback;
	
	@ElementCollection
	private List<String> feedbackImgs;
	
	@ManyToOne
	@JoinColumn(name = "job_id")
	private Job job;
}
