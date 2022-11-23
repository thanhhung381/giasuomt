package giasuomt.demo.comment.service;

import giasuomt.demo.comment.dto.SaveApplicationCommentDto;
import giasuomt.demo.comment.model.ApplicationComment;
import giasuomt.demo.commondata.generic.IGenericService;

public interface IApplicationCommentService extends IGenericService<SaveApplicationCommentDto, ApplicationComment, Long> {
	void deleteApplicationComment(Long id);
	
}
 