package giasuomt.demo.comment.model;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import giasuomt.demo.task.model.Task;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "task_comment")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class TaskComment extends Comment {
	@ManyToOne
	@JoinColumn(name = "task_id")
	@JsonIgnore
	private Task task;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "task_comment_id",nullable = true)
	private TaskComment parentComment;
	@OneToMany(mappedBy = "parentComment")
	@JsonIgnore
	private List<TaskComment> replies = new LinkedList<>();
	private String avatar;

}
