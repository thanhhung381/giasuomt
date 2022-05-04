package giasuomt.demo.task.controller;
import java.util.List;

import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.task.dto.AddObjectToTaskDto;
import giasuomt.demo.task.dto.SaveTaskDto;
import giasuomt.demo.task.dto.ResponseTaskForWebDto;
import giasuomt.demo.task.dto.UpdateFreeTimeDto;
import giasuomt.demo.task.dto.UpdateHourDto;
import giasuomt.demo.task.dto.UpdateLessonDto;

import giasuomt.demo.task.dto.UpdateSalaryDto;
import giasuomt.demo.task.dto.UpdateSubjectDto;
import giasuomt.demo.task.dto.UpdateTaskPlaceAddresseDto;
import giasuomt.demo.task.dto.UpdateTaskSignDto;
import giasuomt.demo.task.dto.UpdateTaskStatusDto;
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

	//API để trả về object Task rỗng cho front-end
	@GetMapping("/getTaskObject")
	public ResponseEntity<Object> getTaskObject() {
		return ResponseHandler.getResponse(new SaveTaskDto(), HttpStatus.OK);
	}
	
	
	//Add Subject to Task
	@PostMapping("/addObject")
	public ResponseEntity<Object> addObject(@Valid @RequestBody AddObjectToTaskDto dto, BindingResult errors) {
		if (((Errors) errors).hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		Task updatedTask = iTaskService.addObject(dto);
		
		return ResponseHandler.getResponse(updatedTask, HttpStatus.OK);
	}
	
	//Delete Subject from Task
	@DeleteMapping("/deleteObject")
	public ResponseEntity<Object> deleteObject(@Valid @RequestBody AddObjectToTaskDto dto, BindingResult errors) {
		if (((Errors) errors).hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Task updatedTask = iTaskService.deleteObject(dto);
		
		return ResponseHandler.getResponse(updatedTask, HttpStatus.OK);
	}
	
	@PutMapping("/updateSubject")
	public ResponseEntity<Object> updateSubject(@RequestBody UpdateSubjectDto dto,BindingResult errors)
	{
		if (((Errors) errors).hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Task updatedSubject = iTaskService.updateSubject(dto);
		
		return ResponseHandler.getResponse(updatedSubject, HttpStatus.OK);
	}
	

	
	@PutMapping("/updateLesson")
	public ResponseEntity<Object> updateLesson(@RequestBody UpdateLessonDto dto,BindingResult errors)
	{
		if (((Errors) errors).hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Task updatedLesson = iTaskService.UpdateLesson(dto);
		
		return ResponseHandler.getResponse(updatedLesson, HttpStatus.OK);
	}
	
	@PutMapping("/updateHour")
	public ResponseEntity<Object> updateHour(@RequestBody UpdateHourDto dto,BindingResult errors)
	{
		if (((Errors) errors).hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Task updatedHour = iTaskService.updateHour(dto);
		
		return ResponseHandler.getResponse(updatedHour, HttpStatus.OK);
	}
	
	@PutMapping("/updateFreeTime")
	public ResponseEntity<Object> updateFreeTime(@RequestBody UpdateFreeTimeDto dto,BindingResult errors)
	{
		if (((Errors) errors).hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Task updatedFreeTime = iTaskService.updateFreeTime(dto);
		
		return ResponseHandler.getResponse(updatedFreeTime, HttpStatus.OK);
	}
	
	@PutMapping("/updateSalary")
	public ResponseEntity<Object> updateSalary(@RequestBody UpdateSalaryDto dto,BindingResult errors)
	{
		if (((Errors) errors).hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Task updatedSalary = iTaskService.updateSalary(dto);
		
		return ResponseHandler.getResponse(updatedSalary, HttpStatus.OK);
	}
	
	@PutMapping("/updateTaskPlaceAddress")
	public ResponseEntity<Object> updateTaskPlaceAddress(@RequestBody UpdateTaskPlaceAddresseDto dto,BindingResult errors)
	{
		if (((Errors) errors).hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Task updateTaskPlaceAddress = iTaskService.updateTaskPlaceAddress(dto);
		
		return ResponseHandler.getResponse(updateTaskPlaceAddress, HttpStatus.OK);
	}
	
	@GetMapping("/findAllAvailabelTaskList")
	public ResponseEntity<Object> findallAvailableTaskList() {
		Set<Task> vailableTaskList = iTaskService.availableTaskList();
		if (vailableTaskList==null)
			return ResponseHandler.getResponse("There is no data.", HttpStatus.BAD_REQUEST);
			
		return ResponseHandler.getResponse(vailableTaskList, HttpStatus.OK);
	}
	
	@GetMapping("/findAllUnavailabelTaskList")
	public ResponseEntity<Object> findallUnavailableTaskList() {
		Set<Task> unavailableTaskList = iTaskService.unavailableTaskList();
		if (unavailableTaskList==null)
			return ResponseHandler.getResponse("There is no data.", HttpStatus.BAD_REQUEST);
			
		return ResponseHandler.getResponse(unavailableTaskList, HttpStatus.OK);
	}
	
	@PutMapping("/updateTaskStatus")
	public ResponseEntity<Object> updateTaskStatusDto(@RequestBody UpdateTaskStatusDto dto,BindingResult errors)
	{
		if (((Errors) errors).hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Task updatedTaskStatus = iTaskService.updateTaskStatusDto(dto);
		
		return ResponseHandler.getResponse(updatedTaskStatus, HttpStatus.OK);
	}
	
	@PutMapping("/updateTaskSign")
	public ResponseEntity<Object> updateTaskSignDto(@RequestBody UpdateTaskSignDto dto,BindingResult errors)
	{
		if (((Errors) errors).hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Task updatedTaskSign = iTaskService.updateTaskSignDto(dto);
		
		return ResponseHandler.getResponse(updatedTaskSign, HttpStatus.OK);
	}
	
	@GetMapping("/findAllAvailableTaskListForWeb")
	public ResponseEntity<Object> findAllAvailableTaskListForWeb() {
		Set<ResponseTaskForWebDto> dtos=iTaskService.findAllAvailableTaskListForWeb();
		if(dtos.isEmpty())
		{
			return ResponseHandler.getResponse("No content", HttpStatus.BAD_REQUEST);
		}
		return ResponseHandler.getResponse(dtos, HttpStatus.OK);
	}
	
}
