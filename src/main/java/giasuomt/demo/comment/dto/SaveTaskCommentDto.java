package giasuomt.demo.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveTaskCommentDto {

	private Long id;

	private String idTask;

	private Long parrentCommentId;
	
	private String content;

}
