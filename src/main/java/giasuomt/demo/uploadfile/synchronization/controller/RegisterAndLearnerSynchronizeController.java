package giasuomt.demo.uploadfile.synchronization.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.finance.model.JobFinance;
import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.uploadfile.synchronization.service.IBillImageSynchronizeService;
import giasuomt.demo.uploadfile.synchronization.service.IRegisterAndLearnerSynchronizeService;
import lombok.AllArgsConstructor;

@RequestMapping("/api/synchronize/synchronizeRegisterAndLearnerSynchronize")
@RestController
@AllArgsConstructor
public class RegisterAndLearnerSynchronizeController {

	private IRegisterAndLearnerSynchronizeService iRegisterAndLearnerSynchronizeService;

	@GetMapping("/findall")
	public ResponseEntity<Object> findAll() {

		Set<RegisterAndLearner> fileEntities = iRegisterAndLearnerSynchronizeService
				.findAllRegisterAndLearnerSynchronizeSynchronizedAvatarAndPublicAndPrivateImg();

		if (fileEntities.isEmpty())
			return ResponseHandler.getResponse("There is no data", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(fileEntities, HttpStatus.OK);

	}

}
