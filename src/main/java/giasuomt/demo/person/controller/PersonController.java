package giasuomt.demo.person.controller;
import java.util.List;
import java.util.Optional;
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
import giasuomt.demo.person.dto.SavePersonDto;
import giasuomt.demo.person.model.Person;
import giasuomt.demo.person.service.IPersonService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/person")
@AllArgsConstructor
public class PersonController {

	private IPersonService iPersonService;

	// Show List of Tutor
	@GetMapping("/findAll")
	public ResponseEntity<Object> findAll() {
		List<Person> persons = iPersonService.findAll();

		if (persons.isEmpty())
			return ResponseHandler.getResponse("There is no data.", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(persons, HttpStatus.OK);
	}

	// Save Tutor
	@PostMapping("/create")
	public ResponseEntity<Object> create(@Valid @RequestBody SavePersonDto dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		Person createdPerson = iPersonService.create(dto);

		return ResponseHandler.getResponse(createdPerson, HttpStatus.CREATED); // Trả về http status là đã thành công
	}

	// Update Tutor
	@PutMapping("/update")
	public ResponseEntity<Object> update(@Valid @RequestBody SavePersonDto dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		Person updatedPerson = iPersonService.update(dto);

		return ResponseHandler.getResponse(updatedPerson, HttpStatus.OK);
	}

	// Delete Tutor
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		iPersonService.deleteById(id);

		return ResponseHandler.getResponse("ok", HttpStatus.BAD_REQUEST);
	}
	
	
	// Find Tutor By Id
	@GetMapping("/findById/{id}")
	public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
		Optional<Person> person = iPersonService.findById(id);

		if (person.isEmpty())
			return ResponseHandler.getResponse("There is no data.", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(person, HttpStatus.OK);
	}
}
