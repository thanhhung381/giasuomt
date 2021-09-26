package giasuomt.demo.task.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import giasuomt.demo.comment.model.Comment;
import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.job.model.Job;

@Entity
@Table(name = "application")
public class Application extends AbstractEntity {
	@ManyToOne
	@JoinColumn(name = "task_id")
	private Task task;
	
	@ManyToOne
	@JoinColumn(name = "tutor_id")
	private Task tutor;
	
	//Comments
    @OneToMany(mappedBy = "application")
    private Set<Comment> comments;
    
    //Đánh dấu
	@OneToMany(mappedBy = "application", fetch = FetchType.EAGER)
	private Set<ApplicationSign> applicationSigns;

	
	//Application này trở thành Job (nếu có)
	@OneToOne(mappedBy = "application")
	private Job job;
	
	
	
	//getter,setter
	
	public Task getTask() {
		return task;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Task getTutor() {
		return tutor;
	}

	public void setTutor(Task tutor) {
		this.tutor = tutor;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<ApplicationSign> getApplicationSigns() {
		return applicationSigns;
	}

	public void setApplicationSigns(Set<ApplicationSign> applicationSigns) {
		this.applicationSigns = applicationSigns;
	}
}
