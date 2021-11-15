package giasuomt.demo.person.controller;

import java.util.List;

import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;

import giasuomt.demo.person.dto.SavePersonDto;
import giasuomt.demo.person.model.Person;
import giasuomt.demo.person.service.IPersonService;
import giasuomt.demo.uploadfile.model.ResponsiveAvatar;
import giasuomt.demo.uploadfile.service.IAvatarService;
import lombok.AllArgsConstructor;
import lombok.Getter;

@RestController
@RequestMapping(value = "/api/person")
@AllArgsConstructor
public class PersonController extends GenericController<SavePersonDto, Person, Long, BindingResult> {

	private IPersonService iPersonService;

	@GetMapping("/findByTutorCode/{tutorCode}")
	public ResponseEntity<Object> findByTutorCode(@RequestParam("tutorCode") String tutorCode) {
		if (!iPersonService.checkByTutorCodeExist(tutorCode))
			return ResponseHandler.getResponse("cant find any persons", HttpStatus.BAD_GATEWAY);

		Person person = iPersonService.findByTutorCode(tutorCode);

		return ResponseHandler.getResponse(person, HttpStatus.OK);
	}

	@GetMapping("/findByPhones/{phones}")
	public ResponseEntity<Object> findByPhones(@RequestParam("phones") String phones) {
		if (!iPersonService.checkByPhonesExist(phones))
			return ResponseHandler.getResponse("cant find any persons", HttpStatus.BAD_GATEWAY);

		List<Person> persons = iPersonService.findByPhones(phones);

		return ResponseHandler.getResponse(persons, HttpStatus.OK);

	}     

	@GetMapping("/findByEndPhone/{phoneEnd}")
	public ResponseEntity<Object> findByEndPhone(@RequestParam("phoneEnd") String phoneEnd) {

		if (!iPersonService.checkByPhonesExist(phoneEnd.concat("#")))
			return ResponseHandler.getResponse("cant find any persons", HttpStatus.BAD_GATEWAY);

		List<Person> persons = iPersonService.findByEndPhone(phoneEnd);

		return ResponseHandler.getResponse(persons, HttpStatus.OK);

	}
	
	
	@GetMapping("/findByFullname/{fullname}")
	public ResponseEntity<Object> findByFullname(@RequestParam("fullname") String fullname) {

		if (!iPersonService.checkFullnameExistContaining(fullname.toUpperCase()))
			return ResponseHandler.getResponse("cant find any persons", HttpStatus.BAD_GATEWAY);

		List<Person> persons = iPersonService.findByFullnamesContain(fullname.toUpperCase());

		return ResponseHandler.getResponse(persons, HttpStatus.OK);

	}

	

}
