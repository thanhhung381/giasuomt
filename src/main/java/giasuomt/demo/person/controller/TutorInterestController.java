package giasuomt.demo.person.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.person.dto.SaveTutorInterestDto;
import giasuomt.demo.person.model.TutorInterest;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/tutorInterest")
@AllArgsConstructor
public class TutorInterestController extends GenericController<SaveTutorInterestDto, TutorInterest, Long>{
	

}
