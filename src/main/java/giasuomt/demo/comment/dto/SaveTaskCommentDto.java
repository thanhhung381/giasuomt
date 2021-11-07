package giasuomt.demo.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveTaskCommentDto {

	private Long id;

	private Long idTask;

	private Long parrentCommentId;

}
