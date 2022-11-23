package giasuomt.demo.job.model;

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
@Table(name = "job_progress")
@Getter
@Setter
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class JobProgress extends AbstractEntity {
	private String name;
	private String content;
	@ManyToOne
	@JoinColumn(name = "job_id")
	@JsonIgnore
	private Job job;
	

	
	
}
