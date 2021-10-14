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
import giasuomt.demo.educational.dto.SaveSubjectGroupDto;
import giasuomt.demo.educational.model.SubjectGroup;
import giasuomt.demo.educational.service.ISubjectGroupService;
import giasuomt.demo.person.dto.SaveCertificateDto;
import giasuomt.demo.person.model.Certificate;
import lombok.AllArgsConstructor;

@RequestMapping("/api/subjectGroup")
@RestController
@AllArgsConstructor
public class SubjectGroupController extends GenericController<SaveSubjectGroupDto, Long, BindingResult> {

	private ISubjectGroupService iSubjectGroupService;

	@Override
	@GetMapping("/findall")
	public ResponseEntity<Object> findall() {
		List<SubjectGroup> subjectGroups = iSubjectGroupService.findAll();

		if (subjectGroups.isEmpty())
			return ResponseHandler.getResponse("there is no data", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(subjectGroups, HttpStatus.OK);
	}

	@Override
	@PostMapping("/create")
	public ResponseEntity<Object> create(@Valid @RequestBody SaveSubjectGroupDto dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		SubjectGroup createSubjectGroup = iSubjectGroupService.create(dto);

		return ResponseHandler.getResponse(createSubjectGroup, HttpStatus.CREATED);
	}

	@Override
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		/*
		 * if (!iSubjectGroupService.checkExistIdofSubjectGroup(id)) return
		 * ResponseHandler.getResponse("Do not exist SubjectGroup",
		 * HttpStatus.BAD_REQUEST);
		 */

		iSubjectGroupService.deleteById(id);

		return ResponseHandler.getResponse("Delete Successfully", HttpStatus.OK);
	}

	@Override
	@PutMapping("/update")
	public ResponseEntity<Object> update(@Valid @RequestBody SaveSubjectGroupDto dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		SubjectGroup updatedSubjectGroup = iSubjectGroupService.update(dto);

		return ResponseHandler.getResponse(updatedSubjectGroup, HttpStatus.OK);
	}

}
