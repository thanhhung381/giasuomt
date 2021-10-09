package giasuomt.demo.tutor.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.tutor.dto.SaveTutorDto;
import giasuomt.demo.tutor.model.Tutor;
import giasuomt.demo.tutor.service.ITutorService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/tutor")
@AllArgsConstructor
public class TutorController {

	private ITutorService iTutorService;

	// Show List of Tutor
	@GetMapping("/findAll")
	public ResponseEntity<Object> findAll() {
		List<Tutor> tutors = iTutorService.findAll();

		if (tutors.isEmpty())
			return ResponseHandler.getResponse("There is no data.", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(tutors, HttpStatus.OK);
	}

	// Save Tutor
	@PostMapping("/create")
	public ResponseEntity<Object> create(@Valid @RequestBody SaveTutorDto dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		Tutor addedTutor = iTutorService.create(dto);

		return ResponseHandler.getResponse(addedTutor, HttpStatus.CREATED); // Trả về http status là đã thành công
	}

	// Update Tutor
	@PutMapping("/update")
	public ResponseEntity<Object> update(@Valid @RequestBody SaveTutorDto dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		Tutor updatedTutor = iTutorService.update(dto);

		return ResponseHandler.getResponse(updatedTutor, HttpStatus.OK);
	}

	// Delete Tutor
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		iTutorService.deleteById(id);

		return ResponseHandler.getResponse("ok", HttpStatus.BAD_REQUEST);
	}
}
