package giasuomt.demo.job.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.job.dto.SaveJobDto;
import giasuomt.demo.job.dto.UpdateJobResultDto;
import giasuomt.demo.job.model.Job;
import giasuomt.demo.job.service.IJobService;
import giasuomt.demo.task.dto.UpdateLessonDto;
import giasuomt.demo.task.model.Task;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/job")
public class JobController extends GenericController<SaveJobDto,Job, Long, BindingResult> {
		
	private IJobService iJobService;
	
	@PutMapping("/updateJobResult")
	public ResponseEntity<Object> updateJobResult(@RequestBody UpdateJobResultDto dto,BindingResult errors)
	{
		if (((Errors) errors).hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Job updatedJob = iJobService.updateJobResult(dto);
		
		return ResponseHandler.getResponse(updatedJob, HttpStatus.OK);
	}
	
}
