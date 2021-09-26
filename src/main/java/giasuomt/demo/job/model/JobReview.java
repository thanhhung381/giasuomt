package giasuomt.demo.job.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import giasuomt.demo.commondata.model.AbstractEntity;

@Entity
@Table(name = "job_review")
public class JobReview extends AbstractEntity {
	@ManyToOne
	@JoinColumn(name = "job_id")
	private Job job;
}
