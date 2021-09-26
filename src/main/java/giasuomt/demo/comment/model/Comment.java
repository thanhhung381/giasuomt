package giasuomt.demo.comment.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.task.model.Application;
import giasuomt.demo.task.model.Task;

@Entity
@Table(name = "comment")
public class Comment extends AbstractEntity {
	private String content;
	
	@OneToMany(mappedBy = "comment", fetch = FetchType.EAGER)
	private Set<CommentEmotion> commentEmotions;
	
		
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment parentComment;     
 
    @OneToMany(mappedBy = "parentComment")
    private Set<Comment> replies = new HashSet<>();
    
    
	@ManyToOne
	@JoinColumn(name = "task_id")
	private Task task;
	
	
	@ManyToOne
	@JoinColumn(name = "application_id")
	private Application application;
	
	
	
	
    //setter,getter
	
	public String getContent() {
		return content;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<CommentEmotion> getCommentEmotions() {
		return commentEmotions;
	}

	public void setCommentEmotions(Set<CommentEmotion> commentEmotions) {
		this.commentEmotions = commentEmotions;
	}

	public Comment getParentComment() {
		return parentComment;
	}

	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}

	public Set<Comment> getReplies() {
		return replies;
	}

	public void setReplies(Set<Comment> replies) {
		this.replies = replies;
	}
    
    
}
