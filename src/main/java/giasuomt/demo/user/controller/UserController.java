package giasuomt.demo.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.person.dto.UpdateRegisteredSubject;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.uploadfile.service.IAvatarService;
import giasuomt.demo.user.dto.SaveUserDto;
import giasuomt.demo.user.dto.UpdateRegisterAndLearnerForUser;
import giasuomt.demo.user.dto.UpdateTutorForUser;
import giasuomt.demo.user.model.User;
import giasuomt.demo.user.service.IUserService;
import lombok.AllArgsConstructor;

@RequestMapping("/api/createUser")
@RestController
@AllArgsConstructor
public class UserController extends GenericController<SaveUserDto, User, Long, BindingResult> {
	
	
	private IUserService iUserService;
	
	@PutMapping("/updateTutorForUser")
	public ResponseEntity<Object> updateTutorForUser(@RequestBody UpdateTutorForUser dto,
			BindingResult errors) {

		User tutorUpdateForUser = iUserService.updateTutorForUser(dto);

		if (errors.hasErrors()) {
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		}

		return ResponseHandler.getResponse(tutorUpdateForUser, HttpStatus.OK);

	}
	
	@PutMapping("/updateRegisterAndLearnerForUser")
	public ResponseEntity<Object> updateRegisterAndLearnerForUser(@RequestBody UpdateRegisterAndLearnerForUser dto,
			BindingResult errors) {

		User registerAndLearnerUpdateForUser = iUserService.updateRegisterAndLearnerForUser(dto);

		if (errors.hasErrors()) {
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		}

		return ResponseHandler.getResponse(registerAndLearnerUpdateForUser, HttpStatus.OK);

	}
	
}
