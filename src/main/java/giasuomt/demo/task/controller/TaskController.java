package giasuomt.demo.task.controller;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.task.dto.SaveTaskDto;
import giasuomt.demo.task.model.Task;
import giasuomt.demo.task.service.ITaskService;
import lombok.AllArgsConstructor;

@RequestMapping("/api/task")
@RestController
@AllArgsConstructor
public class TaskController extends GenericController<SaveTaskDto, Task, Long, BindingResult> {
	private ITaskService iGenericService;


	@GetMapping("/findByTaskCode/{taskCode}")
	public ResponseEntity<Object> findByTaskCode(@RequestParam("taskCode") String taskCode) {
		if (!iGenericService.checkTaskCodeExist(taskCode))
			return ResponseHandler.getResponse("cant find any tasks", HttpStatus.BAD_REQUEST);
		Task task = iGenericService.findByTaskCode(taskCode);
		return ResponseHandler.getResponse(task, HttpStatus.OK);
	}

}
