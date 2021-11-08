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
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Comment extends AbstractEntity {
	
	private String content;
	
//	@OneToMany(mappedBy = "comment", fetch = FetchType.EAGER)
//	private Set<CommentEmotion> commentEmotions;

	
    
    
}
