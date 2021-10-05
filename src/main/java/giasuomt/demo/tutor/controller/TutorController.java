package giasuomt.demo.tutor.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.tutor.dto.CreateTutorDTO;
import giasuomt.demo.tutor.model.Tutor;
import giasuomt.demo.tutor.service.ITutorService;

@RestController //Tức là server sẽ trả JSON về client
@RequestMapping("/api/tutor") //Ghi nhận yêu cầu gọi api của Client
public class TutorController {
	@Autowired
	private ITutorService service;
	
	@GetMapping("")
	public ResponseEntity<Object> findAll() {
		List<Tutor> tutors = service.findAll();
		if (tutors.isEmpty())
			return ResponseHandler.getResponse("There is no data.", HttpStatus.OK);
		return ResponseHandler.getResponse(tutors, HttpStatus.OK);
	}
	
	@PostMapping("/saveTutor")
	public ResponseEntity<Object> saveTutor(@Valid @RequestBody CreateTutorDTO dto, BindingResult bindingResult){ 
		if(bindingResult.hasErrors())
			return ResponseHandler.getResponse(bindingResult, HttpStatus.BAD_REQUEST); 
		Tutor addedTutor = service.saveTutor(dto);	
		return ResponseHandler.getResponse(addedTutor, HttpStatus.CREATED); 
	} 
}
