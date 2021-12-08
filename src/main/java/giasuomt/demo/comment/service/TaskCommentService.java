package giasuomt.demo.comment.service;

import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import giasuomt.demo.comment.dto.SaveTaskCommentDto;
import giasuomt.demo.comment.model.TaskComment;
import giasuomt.demo.comment.repository.ITaskCommentRepository;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.task.model.Task;
import giasuomt.demo.task.repository.ITaskRepository;
import io.swagger.annotations.Authorization;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskCommentService extends GenericService<SaveTaskCommentDto, TaskComment, Long>
		implements ITaskCommentService {

	private MapDtoToModel mapDtoToModel;

	private ITaskRepository iTaskRepository;

	private ITaskCommentRepository iTaskCommentRepository;

	@Override
	public TaskComment create(SaveTaskCommentDto dto) {

		TaskComment taskComment = new TaskComment();

		taskComment = (TaskComment) mapDtoToModel.map(dto, taskComment);

		if (dto.getParrentCommentId() == 0)// comment đầu tiên
		{
			taskComment.setParentComment(null);
		} else {
			taskComment.setParentComment(iTaskCommentRepository.getOne(dto.getParrentCommentId()));
		}

		taskComment.setTask(iTaskRepository.getOne(dto.getIdTask()));

		return save(dto, taskComment);
	}

	@Override
	public TaskComment update(SaveTaskCommentDto dto) {

		TaskComment taskComment = iTaskCommentRepository.getOne(dto.getId());

		taskComment = (TaskComment) mapDtoToModel.map(dto, taskComment);

		if (dto.getParrentCommentId() == 0)// comment đầu tiên //nếu nhập sai id thì có thể validation sau
		{
			taskComment.setParentComment(null);
		} else {
			taskComment.setParentComment(iTaskCommentRepository.getOne(dto.getParrentCommentId()));
		}

		taskComment.setTask(iTaskRepository.getOne(dto.getIdTask()));
		taskComment.setContent(dto.getContent());

		return save(dto, taskComment);
	}

	public void deleteTaskComment(Long id) {
 
		iTaskCommentRepository.deleteByParrentComment(id);

		iTaskCommentRepository.deleteById(id);
	}

	@Override
	public List<TaskComment> createAll(List<SaveTaskCommentDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
