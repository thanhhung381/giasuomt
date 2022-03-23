package giasuomt.demo.person.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.commondata.generic.StringUltilsForAreaID;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.person.dto.ResponseTutorForWeb;
import giasuomt.demo.person.dto.SaveTutorDto;
import giasuomt.demo.person.dto.UpdateRegisteredSubject;
import giasuomt.demo.person.dto.UpdateTutorAvatar;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.person.service.ITutorService;
import lombok.AllArgsConstructor;
import lombok.Getter;

@RestController
@RequestMapping(value = "/api/tutor")
@AllArgsConstructor
public class TutorController extends GenericController<SaveTutorDto, Tutor, Long, BindingResult> {

	private ITutorService iTutorService;

	@GetMapping("/findByTutorCode/{tutorCode}")
	public ResponseEntity<Object> findByTutorCode(@RequestParam("tutorCode") Long tutorCode) {

		Tutor tutor = iTutorService.findByTutorCode(tutorCode);

		if (tutor == null)
			return ResponseHandler.getResponse("cant find any tutors", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(tutor, HttpStatus.OK);
	}

	@GetMapping("/findByPhoneNumber/{phoneNumber}")
	public ResponseEntity<Object> findByPhones(@RequestParam("phoneNumber") String phoneNumber) {

		List<Tutor> tutors = iTutorService.findByPhoneNumber(phoneNumber);

		if (tutors.isEmpty())
			return ResponseHandler.getResponse("cant find any tutors", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(tutors, HttpStatus.OK);

	}

	@GetMapping("/findByEndPhoneNumber/{endPhoneNumber}")
	public ResponseEntity<Object> findByEndPhone(@RequestParam("endPhoneNumber") String endPhoneNumber) {

		List<Tutor> tutors = iTutorService.findByEndPhoneNumber(endPhoneNumber);

		if (tutors.isEmpty())
			return ResponseHandler.getResponse("cant find any tutors", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(tutors, HttpStatus.OK);

	}

	@GetMapping("/findByFullName/{fullName}")
	public ResponseEntity<Object> findByFullnameAndReturnObject(@RequestParam("fullName") String fullName) {

		List<Tutor> tutors = iTutorService.findByFullNameContain(fullName.toUpperCase());

		if (tutors.isEmpty()) {
			List<Tutor> tutorsByEngName = iTutorService
					.findByEnglishFullName(StringUltilsForAreaID.removeAccent(fullName.toUpperCase()));
			if (tutorsByEngName.isEmpty())
				return ResponseHandler.getResponse("cant find any tutors", HttpStatus.BAD_REQUEST);
			return ResponseHandler.getResponse(tutorsByEngName, HttpStatus.OK);
		}

		return ResponseHandler.getResponse(tutors, HttpStatus.OK);

	}

	@GetMapping("/findByFullNameAndReturnName/{fullNameShowName}")
	public ResponseEntity<Object> findByFullnameAndReturnFullName(
			@RequestParam("fullNameShowName") String fullNameShowName) {

		List<String> tutorNames = iTutorService.findByfullnameAndShowFullName(fullNameShowName.toUpperCase());

		if (tutorNames.isEmpty()) {
			List<String> tutorEngNames = iTutorService.findByEngfullnameAndShowFullName(
					StringUltilsForAreaID.removeAccent(fullNameShowName.toUpperCase()));
			if (tutorEngNames.isEmpty())
				return ResponseHandler.getResponse("cant find any tutors", HttpStatus.BAD_REQUEST);
			return ResponseHandler.getResponse(tutorEngNames, HttpStatus.OK);
		}

		return ResponseHandler.getResponse(tutorNames, HttpStatus.OK);

	}

	@PutMapping("/updateRegisteredSubject")
	public ResponseEntity<Object> updateRegisteredSubject(@RequestBody UpdateRegisteredSubject dto,
			BindingResult errors) {

		Tutor tutorUpdateRegisteredSubject = iTutorService.updateRegisteredSubjects(dto);

		if (errors.hasErrors()) {
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		}

		return ResponseHandler.getResponse(tutorUpdateRegisteredSubject, HttpStatus.OK);

	}
	
	@PutMapping("/updateAvatarTutor")
	public ResponseEntity<Object> updateAvatarTutor(@RequestBody UpdateTutorAvatar dto,
			BindingResult errors) {

		Tutor tutor = iTutorService.updateAvatarTutor(dto);

		if (errors.hasErrors()) {
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		}

		return ResponseHandler.getResponse( tutor, HttpStatus.OK);

	}
	
	@GetMapping("/findAllTutorForWeb")
	public ResponseEntity<Object> findAllTutorForWeb()
	{
		List<ResponseTutorForWeb> list=iTutorService.findAllTutorForWeb();
		if(list.isEmpty())
			return ResponseHandler.getResponse("No content", HttpStatus.BAD_REQUEST );
		
		return ResponseHandler.getResponse(list, HttpStatus.OK);
	}
}
