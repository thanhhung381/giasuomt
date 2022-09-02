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

import giasuomt.demo.task.model.Application;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "application_comment")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class ApplicationComment extends Comment {
	@ManyToOne
	@JoinColumn(name = "application_id")
	@JsonIgnore
	private Application application;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "application_comment_id",nullable = true)
	private ApplicationComment parentComment;
	@OneToMany(mappedBy = "parentComment")
	@JsonIgnore
	private List<ApplicationComment> replies = new LinkedList<>();

}
 