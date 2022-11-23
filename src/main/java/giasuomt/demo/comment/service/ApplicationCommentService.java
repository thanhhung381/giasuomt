package giasuomt.demo.comment.service;

import org.springframework.stereotype.Service;

import giasuomt.demo.comment.dto.SaveApplicationCommentDto;
import giasuomt.demo.comment.model.ApplicationComment;
import giasuomt.demo.comment.repository.IApplicationCommentRepository;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.task.repository.IApplicationRepository;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class ApplicationCommentService extends GenericService<SaveApplicationCommentDto, ApplicationComment, Long>
		implements IApplicationCommentService {
	private IApplicationCommentRepository iApplicationCommentRepository;
	private IApplicationRepository iApplicationRepository;
	private MapDtoToModel mapDtoToModel;
	public ApplicationComment create(SaveApplicationCommentDto dto) {
		ApplicationComment applicationComment = new ApplicationComment();
		applicationComment = (ApplicationComment) mapDtoToModel.map(dto, applicationComment);
		applicationComment.setApplication(iApplicationRepository.getOne(dto.getIdApplication()));
		if (dto.getIdParentComment() == 0)// comment đầu tiên
		{
			applicationComment.setParentComment(null);
		} else {
			applicationComment.setParentComment(iApplicationCommentRepository.getOne(dto.getIdParentComment()));
		}
		return save(dto, applicationComment);
	}

	@Override
	public ApplicationComment update(SaveApplicationCommentDto dto) {
		ApplicationComment applicationComment = iApplicationCommentRepository.getOne(dto.getId());
		applicationComment = (ApplicationComment) mapDtoToModel.map(dto, applicationComment);
		applicationComment.setApplication(iApplicationRepository.getOne(dto.getIdApplication()));
		if (dto.getIdParentComment() == 0)// comment đầu tiên
		{
			applicationComment.setParentComment(null);
		} else {
			applicationComment.setParentComment(iApplicationCommentRepository.getOne(dto.getIdParentComment()));
		}
		return save(dto, applicationComment);
	}

	@Override
	public void deleteApplicationComment(Long id) {
		iApplicationCommentRepository.deleteByParentComment(id);
		iApplicationCommentRepository.deleteById(id);
		
	}
}
