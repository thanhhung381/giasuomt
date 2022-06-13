package giasuomt.demo.comment.dto;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import giasuomt.demo.comment.model.ApplicationComment;
import giasuomt.demo.task.model.Application;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveApplicationCommentDto {

	private Long id;

	private String idApplication;

	private Long idParentComment;
	
	private String content;
 
}
