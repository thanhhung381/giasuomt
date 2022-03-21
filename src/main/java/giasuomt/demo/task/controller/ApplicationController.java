package giasuomt.demo.task.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.task.dto.SaveApplicationDto;
import giasuomt.demo.task.dto.UpdateApplicationSignDto;
import giasuomt.demo.task.dto.UpdateTaskSignDto;
import giasuomt.demo.task.model.Application;
import giasuomt.demo.task.model.Task;
import giasuomt.demo.task.service.IApplicationService;
import lombok.AllArgsConstructor;

@RequestMapping("/api/application")
@RestController
@AllArgsConstructor
public class ApplicationController extends GenericController<SaveApplicationDto, Application, Long, BindingResult> {
	
	private IApplicationService iApplicationService;

	@DeleteMapping("/deleteApplication/{id}")
	public ResponseEntity<Object> deleteByApplicationId(@PathVariable("id") Long id) {
		if (!iApplicationService.checkExistIdOfT(id))
			return ResponseHandler.getResponse("Don't have any Area id", HttpStatus.BAD_REQUEST);

		iApplicationService.deleteByIdAppliction(id);

		return ResponseHandler.getResponse("Delete Successfully", HttpStatus.OK);
	}
	
	@PutMapping("/updateApplicationSign")
	public ResponseEntity<Object> updateApplicationSign(@RequestBody UpdateApplicationSignDto dto,BindingResult errors)
	{
		if (((Errors) errors).hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Application updatedApplicationSign = iApplicationService.updateApplicationSign(dto);
		
		return ResponseHandler.getResponse(updatedApplicationSign, HttpStatus.OK);
	}
}
