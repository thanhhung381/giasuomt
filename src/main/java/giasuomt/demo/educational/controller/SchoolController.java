package giasuomt.demo.educational.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.educational.dto.SaveSchoolDto;
import giasuomt.demo.educational.model.School;

@RequestMapping("/api/school")
@RestController
public class SchoolController extends GenericController<SaveSchoolDto, School, Long, BindingResult> {

}
