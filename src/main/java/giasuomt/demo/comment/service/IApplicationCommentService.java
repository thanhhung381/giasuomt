package giasuomt.demo.comment.service;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import giasuomt.demo.comment.dto.SaveApplicationCommentDto;
import giasuomt.demo.comment.model.ApplicationComment;
import giasuomt.demo.commondata.generic.IGenericService;

public interface IApplicationCommentService extends IGenericService<SaveApplicationCommentDto, ApplicationComment, Long> {
	
	
	
	void deleteApplicationComment(Long id);
	
}
 