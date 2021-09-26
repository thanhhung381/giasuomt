package giasuomt.demo.job.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import giasuomt.demo.commondata.model.AbstractEntity;

@Entity
@Table(name = "job_progress")
public class JobProgress extends AbstractEntity {
	private String name;
	
	private String content;

	@ManyToOne
	@JoinColumn(name = "job_id")
	private Job job;
	
	//getter,setter
	
	public String getName() {
		return name;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
