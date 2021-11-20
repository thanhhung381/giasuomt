package giasuomt.demo.task.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.task.dto.SaveTaskDto;
import giasuomt.demo.task.model.Task;
import giasuomt.demo.task.service.ITaskService;
import lombok.AllArgsConstructor;

@RequestMapping("/api/task")
@RestController
@AllArgsConstructor
public class TaskController extends GenericController<SaveTaskDto, Task, Long, BindingResult> {
	private ITaskService iTaskService;

	@GetMapping("/findByTaskCode/{taskCode}")
	public ResponseEntity<Object> findByTaskCode(@RequestParam("taskCode") String taskCode) {

		Task task = iTaskService.findByTaskCode(taskCode);

		if (task == null)
			return ResponseHandler.getResponse("cant find any tasks", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(task, HttpStatus.OK);
	}

//	@GetMapping("/findRegistersAndLearnersById/{id}")
//	public ResponseEntity<Object> findRegistersAndLearners(@RequestParam("id") Long id) {
//
//		List<RegisterAndLearner> registers = iTaskService.findRegistersById(id);
//		
//		List<RegisterAndLearner> learners = iTaskService.findLearnersById(id);
//
//		if (registers == null && learners == null)
//			return ResponseHandler.getResponse("cant find any tasks", HttpStatus.BAD_REQUEST);
//
//		return ResponseHandler.getResponse("registers", registers, "learners", learners, HttpStatus.OK);
//	}
}
