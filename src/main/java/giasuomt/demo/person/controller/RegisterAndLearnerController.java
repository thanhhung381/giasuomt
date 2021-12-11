package giasuomt.demo.person.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.commondata.generic.StringUltilsForAreaID;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.person.dto.SaveRegisterAndLearnerDto;
import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.person.service.IRegisterAndLearnerService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/registerAndLearner")
@AllArgsConstructor
public class RegisterAndLearnerController
		extends GenericController<SaveRegisterAndLearnerDto, RegisterAndLearner, Long, BindingResult> {

	private IRegisterAndLearnerService iRegisterAndLearnerService;

	@GetMapping("/findByPhoneNumber/{phoneNumber}")
	public ResponseEntity<Object> findByPhones(@RequestParam("phoneNumber") String phoneNumber) {

		List<RegisterAndLearner> registerAndLearners = iRegisterAndLearnerService.findByPhoneNumber(phoneNumber);

		if (registerAndLearners.isEmpty())
			return ResponseHandler.getResponse("cant find any Register and Learner", HttpStatus.OK);

		return ResponseHandler.getResponse(registerAndLearners, HttpStatus.OK);

	}

	@GetMapping("/findByEndPhoneNumber/{endPhoneNumber}")
	public ResponseEntity<Object> findByEndPhone(@RequestParam("endPhoneNumber") String endPhoneNumber) {

		List<RegisterAndLearner> registerAndLearners = iRegisterAndLearnerService.findByEndPhoneNumber(endPhoneNumber);

		if (registerAndLearners.isEmpty())
			return ResponseHandler.getResponse("cant find any Register and Learner", HttpStatus.OK);

		return ResponseHandler.getResponse(registerAndLearners, HttpStatus.OK);

	}
 
	@GetMapping("/findByFullNameAndReturnObject/{fullname}")
	public ResponseEntity<Object> findByFullnameAndReturnObject(@RequestParam("fullname") String fullname) {

		List<RegisterAndLearner> registerAndLearners = iRegisterAndLearnerService
				.findByFullNameContaining(fullname.toUpperCase());

		if (registerAndLearners.isEmpty()) {
			List<RegisterAndLearner> registerAndLearnersWithEnglishFullName = iRegisterAndLearnerService
					.findByEnglishFullNameContaining(StringUltilsForAreaID.removeAccent(fullname.toUpperCase()));
			if (registerAndLearnersWithEnglishFullName.isEmpty())
				return ResponseHandler.getResponse("cant find any Register and Learner", HttpStatus.OK);
			return ResponseHandler.getResponse(registerAndLearnersWithEnglishFullName, HttpStatus.OK);
		}

		return ResponseHandler.getResponse(registerAndLearners, HttpStatus.OK);

	}

	@GetMapping("/findByFullNameAndReturnFullName/{fullname}")
	public ResponseEntity<Object> findByFullnameAndReturnStringFullname(@RequestParam("fullname") String fullname) {

		List<String> registerAndLearners = iRegisterAndLearnerService
				.findByFullNameAndShowFullName(fullname.toUpperCase());

		if (registerAndLearners.isEmpty()) {
			List<String> registerAndLearnersWithEnglishFullName = iRegisterAndLearnerService
					.findByEnglishNameAndShowEngLishFullName(StringUltilsForAreaID.removeAccent(fullname.toUpperCase()));
			if (registerAndLearnersWithEnglishFullName.isEmpty())
				return ResponseHandler.getResponse("cant find any Register and Learner", HttpStatus.OK);
			return ResponseHandler.getResponse(registerAndLearnersWithEnglishFullName, HttpStatus.OK);
		}

		return ResponseHandler.getResponse(registerAndLearners, HttpStatus.OK);

	}

	@GetMapping("/findByFullNameAndVocativeAndReturnObject/{fullname}/{vocative}")
	public ResponseEntity<Object> findByFullnameAndVocativeAndReturnObject(@RequestParam("fullname") String fullname,
			@RequestParam("vocative") String vocative) {

		List<RegisterAndLearner> registerAndLearners = iRegisterAndLearnerService.findByVocativeAndFullName(vocative,
				fullname.toUpperCase());

		if (registerAndLearners.isEmpty()) {

			List<RegisterAndLearner> registerAndLearnersWithEnglishFullName = iRegisterAndLearnerService
					.findByVocativeAndEnglishFullNameContaining(vocative, StringUltilsForAreaID.removeAccent(fullname.toUpperCase()));
			if (registerAndLearnersWithEnglishFullName.isEmpty())
				return ResponseHandler.getResponse("cant find any Register and Learner", HttpStatus.OK);
			return ResponseHandler.getResponse(registerAndLearnersWithEnglishFullName, HttpStatus.OK);
		}

		return ResponseHandler.getResponse(registerAndLearners, HttpStatus.OK);
	}

	@GetMapping("/findByFullNameAndVocativeAndReturnFullName/{fullname}/{vocative}")
	public ResponseEntity<Object> findByFullnameAndVocativeAndReturnFullName(@RequestParam("fullname") String fullname,
			@RequestParam("vocative") String vocative) {

		List<String> registerAndLearners = iRegisterAndLearnerService.findByVocativeAndFullNameAndShowFullName(vocative,
				fullname.toUpperCase());

		if (registerAndLearners.isEmpty()) {

			List<String> RegisterAndLearnerServiceWithEnglishFullName = iRegisterAndLearnerService
					.findByVocativeAndEnglishFullNameAndShowFullName(vocative, StringUltilsForAreaID.removeAccent(fullname.toUpperCase()));
			if (RegisterAndLearnerServiceWithEnglishFullName.isEmpty())
				return ResponseHandler.getResponse("cant find any Register and Learner", HttpStatus.OK);
			return ResponseHandler.getResponse(RegisterAndLearnerServiceWithEnglishFullName, HttpStatus.OK);

		}

		return ResponseHandler.getResponse(registerAndLearners, HttpStatus.OK);
	}

}
