package giasuomt.demo.tutor.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.location.dto.CreateAreaDTO;
import giasuomt.demo.location.dto.FindingDtoArea;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.tutor.dto.CreateTutorDto;
import giasuomt.demo.tutor.dto.TutorWithStudent;
import giasuomt.demo.tutor.model.Tutor;
import giasuomt.demo.tutor.service.ITutorService;

@RestController
@RequestMapping("/api/Tutor")
public class TutorController {
	@Autowired
	private ITutorService iTutorService;
	
	//Show List of Tutor
	@GetMapping("")
	public ResponseEntity<Object> findAll() {
		List<Tutor> tutors = iTutorService.findAll();
		if (tutors.isEmpty())

			return ResponseHandler.getResponse("There is no data.", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(tutors, HttpStatus.OK);
	}
	
	// Save Tutor
		@PostMapping("")
		public ResponseEntity<Object> save(@Valid @RequestBody CreateTutorDto dto, BindingResult errors) {
			if (errors.hasErrors())
				return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST); // Trả về các messages lỗi, kèm theo
																					// HttpStatus BAD REQUEST [xem trong
																					// ResponsHandler.java]
			Tutor addedTutor =iTutorService.save(dto);
			
			List<TutorWithStudent> lists=iTutorService.findalll();
			
			return ResponseHandler.getResponse("Save successfully", HttpStatus.CREATED); // Trả về http status là đã thành công
		}
		//Delete Tutor
		@DeleteMapping("/{id}")
		public ResponseEntity<Object> deleteByIdofTutor(@PathVariable("id") Long id)
		{
			iTutorService.deleteById(id);
			
			
			
			return ResponseHandler.getResponse("ok", HttpStatus.BAD_REQUEST);
		}
		
		
		
		
		//Show Tutor With Difference infor
		@GetMapping("/showTutorWithDifferInfo")
		public ResponseEntity<Object> ShowTutorFull() {
			List<TutorWithStudent> lists=iTutorService.findalll();
			if (lists.isEmpty())

				return ResponseHandler.getResponse("There is no data.", HttpStatus.BAD_REQUEST);

			return ResponseHandler.getResponse(lists, HttpStatus.OK);
		}
		
	
}
