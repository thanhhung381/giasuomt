package giasuomt.demo.comment.controller;

import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.comment.dto.SaveTaskCommentDto;
import giasuomt.demo.comment.model.TaskComment;
import giasuomt.demo.comment.repository.ITaskCommentRepository;
import giasuomt.demo.comment.service.ITaskCommentService;
import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.staff.dto.TaskListResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/taskcomment")
@AllArgsConstructor
public class TaskCommentController extends GenericController<SaveTaskCommentDto, TaskComment, Long> {

	private ITaskCommentService iTaskCommentService;

	@DeleteMapping("/deleteByTaskCommnent/{idTask}")
	public ResponseEntity<Object> deleteTaskComment(@RequestParam("idTask") Long idTask) {

		iTaskCommentService.deleteTaskComment(idTask);

		return ResponseHandler.getResponse("Delete successfully", HttpStatus.OK);
	}

}
