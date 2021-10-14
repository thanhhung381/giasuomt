package giasuomt.demo.educational.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.educational.dto.SaveSubjectDto;
import giasuomt.demo.educational.model.Subject;
import lombok.AllArgsConstructor;

@RequestMapping("/api/subject")
@RestController
@AllArgsConstructor
public class SubjectController extends GenericController<SaveSubjectDto, Subject,Long, BindingResult> {
	
	
	
	
}
