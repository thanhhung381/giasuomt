package giasuomt.demo.uploadfile.synchronization.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.uploadfile.service.IAvatarAwsService;
import giasuomt.demo.uploadfile.synchronization.service.IUserAvatarSynchronizeService;
import giasuomt.demo.user.model.User;
import lombok.AllArgsConstructor;

@RequestMapping("/api/synchronize/synchronizeUser")
@RestController
@AllArgsConstructor
public class UserAvatarSynchronizeController {
	
	
	private IUserAvatarSynchronizeService iUserAvatarSynchronize;
	
	
	@GetMapping("/findall")
	public ResponseEntity<Object> findAll() {

		Set<User> fileEntities = iUserAvatarSynchronize.findAllUserSynchronized();

		if (fileEntities.isEmpty())
			return ResponseHandler.getResponse("There is no data", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(fileEntities, HttpStatus.OK);

	}
	
}
