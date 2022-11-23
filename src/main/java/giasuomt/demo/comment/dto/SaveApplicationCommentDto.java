package giasuomt.demo.comment.dto;

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
