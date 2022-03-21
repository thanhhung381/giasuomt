package giasuomt.demo.user.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.person.dto.UpdateRegisteredSubject;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.security.jwt.JwtUltils;
import giasuomt.demo.uploadfile.service.IAvatarAwsService;
import giasuomt.demo.user.dto.ResponseUser;
import giasuomt.demo.user.dto.ResponseUserWithBasicInfor;
import giasuomt.demo.user.dto.SaveUserDto;
import giasuomt.demo.user.dto.UpdateRegisterAndLearnerForUser;
import giasuomt.demo.user.dto.UpdateAndDeleteRoleForUser;
import giasuomt.demo.user.dto.UpdateAvatarUser;
import giasuomt.demo.user.dto.UpdateTutorForUser;
import giasuomt.demo.user.dto.findJWT;
import giasuomt.demo.user.model.User;
import giasuomt.demo.user.repository.IUserRepository;
import giasuomt.demo.user.service.IUserService;
import lombok.AllArgsConstructor;

@RequestMapping("/api/user")
@RestController
@AllArgsConstructor
public class UserController extends GenericController<SaveUserDto, User, Long, BindingResult> {
	
	private JwtUltils jwtUltils;
	
	private IUserRepository iUserRepository;
	
	private MapDtoToModel mapDtoToModel;
	
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
	
	@PreAuthorize("hasAuthority('admin-role')")
	@PutMapping("/updateRoleForUser")
	public ResponseEntity<Object> updateAndDeleteRoleForUser(@RequestBody UpdateAndDeleteRoleForUser dto,
			BindingResult errors) {

		User roleUpdateForUser = iUserService.updateRoleForUser(dto);

		if (errors.hasErrors()) {
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		}

		return ResponseHandler.getResponse(roleUpdateForUser, HttpStatus.OK);

	}
	
	@PreAuthorize("hasAuthority('admin-role')")
	@DeleteMapping("/deleteRoleForUser")
	public ResponseEntity<Object> deleteRoleForUser(@RequestBody UpdateAndDeleteRoleForUser dto,
			BindingResult errors) {

		User roleDeleteForUser = iUserService.deleteRoleForUser(dto);

		if (errors.hasErrors()) {
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		}

		return ResponseHandler.getResponse(roleDeleteForUser, HttpStatus.OK);

	}
	
	@PostMapping("/findByJWT")
	public ResponseEntity<Object> findByJWT(@RequestBody findJWT dto)
	{
	
		if(jwtUltils.validateJWtToken(dto.getJwt()))
		{
			String username=jwtUltils.getUsernameToken(dto.getJwt());
			Optional<User> user =iUserRepository.findByUsername(username);
			if(user.isEmpty())
			{
				return ResponseHandler.getResponse("Invalid username", HttpStatus.BAD_REQUEST);
			}
			else
			{
				ResponseUser responseUser=new ResponseUser();
				 responseUser.setEmail(user.get().getEmail());
				 responseUser.setPhones(user.get().getPhones());
				 responseUser.setRegisterAndLearner(user.get().getRegisterAndLearner());
				 responseUser.setUsername(user.get().getUsername());
				 responseUser.setAvatar(user.get().getAvatar());
				 
				 
				 
				
				return ResponseHandler.getResponse(responseUser, HttpStatus.OK);
			}
		}
		
		return ResponseHandler.getResponse("Invalid jwt", HttpStatus.BAD_REQUEST);
		
	}
	
	@PutMapping("/updateAvatarUser")
	public ResponseEntity<Object> updateAvatarUser(@RequestBody UpdateAvatarUser dto,
			BindingResult errors) {

		User roleDeleteForUser = iUserService.updateAvartarUser(dto);

		if (errors.hasErrors()) {
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		}

		return ResponseHandler.getResponse(roleDeleteForUser, HttpStatus.OK);

	}
	
	@GetMapping("/findUserbyUsername/{username}")
	public ResponseEntity<Object> findUserByUsername(@PathVariable("username") String username)
	{
	
	
			Optional<User> user =iUserRepository.findByUsername(username);
			if(user.isEmpty())
			{
				return ResponseHandler.getResponse("Invalid username", HttpStatus.BAD_REQUEST);
			}
			else
			{
				ResponseUserWithBasicInfor responseUser=new ResponseUserWithBasicInfor();
				 responseUser.setEmail(user.get().getEmail());
				 responseUser.setPhones(user.get().getPhones());
				 responseUser.setUsername(user.get().getUsername());
				 responseUser.setAvatar(user.get().getAvatar());
				 responseUser.setId(user.get().getId());
				 
				 
				 
				
				return ResponseHandler.getResponse(responseUser, HttpStatus.OK);
			}
		
		
	
		
	}
	
}
