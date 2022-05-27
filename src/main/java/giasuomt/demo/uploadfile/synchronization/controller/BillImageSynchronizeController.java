package giasuomt.demo.uploadfile.synchronization.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.finance.model.JobFinance;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.uploadfile.synchronization.service.IBillImageSynchronizeService;
import giasuomt.demo.uploadfile.synchronization.service.ITutorAvatarSynchronizeService;
import lombok.AllArgsConstructor;

@RequestMapping("/api/synchronize/synchronizeBillImge")
@RestController
@AllArgsConstructor
public class BillImageSynchronizeController {

	private IBillImageSynchronizeService iBillImageSynchronizeService;
	
	
	@GetMapping("/findall")
	public ResponseEntity<Object> findAll() {

		Set<JobFinance> fileEntities = iBillImageSynchronizeService.findAllJobFinanceSynchronized();

		if (fileEntities.isEmpty())
			return ResponseHandler.getResponse("There is no data", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(fileEntities, HttpStatus.OK);

	}
}
