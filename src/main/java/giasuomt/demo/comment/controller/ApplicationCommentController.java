package giasuomt.demo.comment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.comment.dto.SaveApplicationCommentDto;
import giasuomt.demo.comment.model.ApplicationComment;
import giasuomt.demo.comment.service.IApplicationCommentService;
import giasuomt.demo.comment.service.ITaskCommentService;
import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/applicationcomment")
@AllArgsConstructor
public class ApplicationCommentController extends GenericController<SaveApplicationCommentDto, ApplicationComment, Long> {
	private IApplicationCommentService iApplicationCommentService;
	@DeleteMapping("/delete/{idApplicationComment}")
	public ResponseEntity<Object> deleteTaskComment(@RequestParam("idApplicationComment") Long idTaskComment) {
		iApplicationCommentService.deleteApplicationComment(idTaskComment);
		return ResponseHandler.getResponse("Delete successfully", HttpStatus.OK);
	}
	
}
