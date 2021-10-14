package giasuomt.demo.educational.controller;

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

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.educational.dto.SaveSubjectDto;
import giasuomt.demo.educational.dto.SaveSubjectGroupDto;
import giasuomt.demo.educational.model.Subject;
import giasuomt.demo.educational.model.SubjectGroup;
import giasuomt.demo.educational.service.ISubjectGroupService;
import giasuomt.demo.educational.service.ISubjectService;
import lombok.AllArgsConstructor;

@RequestMapping("/api/subject")
@RestController
@AllArgsConstructor
public class SubjectController extends GenericController<SaveSubjectDto, Long, BindingResult> {

	private ISubjectService iSubjectService;

	@Override
	@PutMapping("/create")
	public ResponseEntity<Object> create(@Valid @RequestBody SaveSubjectDto dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		Subject createSubject = iSubjectService.create(dto);

		return ResponseHandler.getResponse(createSubject, HttpStatus.CREATED);
	}

	@Override
	@GetMapping("/findall")
	public ResponseEntity<Object> findall() {
		List<Subject> subjects = iSubjectService.findall();

		if (subjects.isEmpty())
			return ResponseHandler.getResponse("there is no data", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(subjects, HttpStatus.OK);
	}

	@Override
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		if (!iSubjectService.checkExistIdofSubject(id))
			return ResponseHandler.getResponse("Do not exist Subject", HttpStatus.BAD_REQUEST);

		iSubjectService.delete(id);

		return ResponseHandler.getResponse("Delete Successfully", HttpStatus.OK);
	}

	@Override
	@PutMapping("/update")
	public ResponseEntity<Object> update(@Valid @RequestBody SaveSubjectDto dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		Subject updatedSubject = iSubjectService.update(dto);

		return ResponseHandler.getResponse(updatedSubject, HttpStatus.OK);
	}

}
