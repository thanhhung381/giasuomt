package giasuomt.demo.task.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import giasuomt.demo.comment.model.Comment;
import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.job.model.Job;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "application")
@Getter
@Setter
public class Application extends AbstractEntity {
	
	@ManyToOne
	@JoinColumn(name = "task_id")
	@JsonIgnore
	private Task task;
	
//	@ManyToOne
//	@JoinColumn(name = "person_id")
//	private Person person;
	
	//Comments
//    @OneToMany(mappedBy = "application")
 //   private Set<Comment> comments;
    
//    //Đánh dấu
//	@OneToMany(mappedBy = "application", fetch = FetchType.EAGER)
//	private Set<ApplicationSign> applicationSigns;

	
	//Application này trở thành Job (nếu có)
//	@OneToOne(mappedBy = "application")
//	private Job job;
	
	
	
	//getter,setter
	
}
