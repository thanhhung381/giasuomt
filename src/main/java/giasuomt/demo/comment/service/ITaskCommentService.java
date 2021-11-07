package giasuomt.demo.comment.service;

import giasuomt.demo.comment.dto.SaveTaskCommentDto;
import giasuomt.demo.comment.model.TaskComment;
import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.task.model.Task;

public interface ITaskCommentService extends IGenericService<SaveTaskCommentDto, TaskComment, Long> {
	public void deleteTaskComment(Long id);
}
