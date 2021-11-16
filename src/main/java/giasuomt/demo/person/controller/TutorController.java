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
import giasuomt.demo.person.dto.SaveTutorDto;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.person.service.ITutorService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/tutor")
@AllArgsConstructor
public class TutorController extends GenericController<SaveTutorDto, Tutor, Long, BindingResult> {

	private ITutorService iTutorService;

	@GetMapping("/findByTutorCode/{tutorCode}")
	public ResponseEntity<Object> findByTutorCode(@RequestParam("tutorCode") String tutorCode) {

		Tutor tutor = iTutorService.findByTutorCode(tutorCode);

		if (tutor == null)
			return ResponseHandler.getResponse("cant find any tutors", HttpStatus.BAD_GATEWAY);

		return ResponseHandler.getResponse(tutor, HttpStatus.OK);
	}

	@GetMapping("/findByPhoneNumber/{phoneNumber}")
	public ResponseEntity<Object> findByPhones(@RequestParam("phoneNumber") String phoneNumber) {

		List<Tutor> tutors = iTutorService.findByPhoneNumber(phoneNumber);

		if (tutors == null)
			return ResponseHandler.getResponse("cant find any tutors", HttpStatus.BAD_GATEWAY);

		return ResponseHandler.getResponse(tutors, HttpStatus.OK);

	}

	@GetMapping("/findByEndPhoneNumber/{endPhoneNumber}")
	public ResponseEntity<Object> findByEndPhone(@RequestParam("endPhoneNumber") String endPhoneNumber) {

		List<Tutor> tutors = iTutorService.findByEndPhoneNumber(endPhoneNumber);

		if (tutors == null)
			return ResponseHandler.getResponse("cant find any tutors", HttpStatus.BAD_GATEWAY);

		return ResponseHandler.getResponse(tutors, HttpStatus.OK);

	}

	@GetMapping("/findByFullName/{fullName}")
	public ResponseEntity<Object> findByFullname(@RequestParam("fullName") String fullName) {

		List<Tutor> tutors = iTutorService.findByFullNameContain(fullName.toUpperCase());

		if (tutors == null)
			return ResponseHandler.getResponse("cant find any tutors", HttpStatus.BAD_GATEWAY);

		return ResponseHandler.getResponse(tutors, HttpStatus.OK);

	}

}
