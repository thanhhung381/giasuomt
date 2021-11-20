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
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.person.dto.SaveRegisterAndLearnerDto;
import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.person.service.IRegisterAndLearnerService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/registerAndLearner")
@AllArgsConstructor
public class RegisterAndLearnerController extends GenericController<SaveRegisterAndLearnerDto, RegisterAndLearner, Long, BindingResult> {
	
	private IRegisterAndLearnerService iRegisterAndLearnerService;
	
	@GetMapping("/findByPhoneNumber/{phoneNumber}")
	public ResponseEntity<Object> findByPhones(@RequestParam("phoneNumber") String phoneNumber) {

		List<RegisterAndLearner> registerAndLearners = iRegisterAndLearnerService.findByPhoneNumber(phoneNumber);

		if (registerAndLearners == null)
			return ResponseHandler.getResponse("cant find any tutors", HttpStatus.BAD_GATEWAY);

		return ResponseHandler.getResponse(registerAndLearners, HttpStatus.OK);

	}
	
	@GetMapping("/findByEndPhoneNumber/{endPhoneNumber}")
	public ResponseEntity<Object> findByEndPhone(@RequestParam("endPhoneNumber") String endPhoneNumber) {

		List<RegisterAndLearner> registerAndLearners = iRegisterAndLearnerService.findByEndPhoneNumber(endPhoneNumber);

		if (registerAndLearners == null)
			return ResponseHandler.getResponse("cant find any tutors", HttpStatus.BAD_GATEWAY);

		return ResponseHandler.getResponse(registerAndLearners, HttpStatus.OK);

	}
}
