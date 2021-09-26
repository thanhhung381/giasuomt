package giasuomt.demo.comment.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import giasuomt.demo.comment.util.Emotion;
import giasuomt.demo.commondata.model.AbstractEntity;

@Entity
@Table(name = "comment_emotion")
public class CommentEmotion extends AbstractEntity {
	private Emotion emotion;
	
	@ManyToOne
	@JoinColumn(name = "comment_id")
	private Comment comment;

	//getter,setter
	
	public Emotion getEmotion() {
		return emotion;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public void setEmotion(Emotion emotion) {
		this.emotion = emotion;
	}	
}
