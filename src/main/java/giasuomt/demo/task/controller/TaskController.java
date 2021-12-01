package giasuomt.demo.task.controller;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.task.dto.AddSubjectToTaskDto;
import giasuomt.demo.task.dto.SaveTaskDto;
import giasuomt.demo.task.model.Task;
import giasuomt.demo.task.service.ITaskService;
import lombok.AllArgsConstructor;

@RequestMapping("/api/task")
@RestController
@AllArgsConstructor
public class TaskController extends GenericController<SaveTaskDto, Task, String, BindingResult> {
	private ITaskService iTaskService;

	@GetMapping("/findByTaskCode/{taskCode}")
	public ResponseEntity<Object> findByTaskCode(@RequestParam("taskCode") String taskCode) {

		Optional<Task> task = iTaskService.findByTaskCode(taskCode);

		if (task == null)
			return ResponseHandler.getResponse("cant find any tasks", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(task, HttpStatus.OK);
	}


	@GetMapping("/getTaskObject")
	public ResponseEntity<Object> getTaskObject() {
		return ResponseHandler.getResponse(new SaveTaskDto(), HttpStatus.OK);
	}
	
	
	//Add Subject to Task
	@PostMapping("/add/addSubjectToTask")
	public ResponseEntity<Object> create(@Valid @RequestBody AddSubjectToTaskDto dto, BindingResult errors) {
		if (((Errors) errors).hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Task updatedTask = iTaskService.addSubjectToTask(dto);
		
		return ResponseHandler.getResponse(updatedTask, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/deleteSubjectFromTask")
	public ResponseEntity<Object> delete(@Valid @RequestBody AddSubjectToTaskDto dto, BindingResult errors) {
		if (((Errors) errors).hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Task updatedTask = iTaskService.deleteSubjectFromTask(dto);
		
		return ResponseHandler.getResponse(updatedTask, HttpStatus.OK);
	}
}
