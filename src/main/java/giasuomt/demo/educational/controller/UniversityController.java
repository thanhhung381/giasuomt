package giasuomt.demo.educational.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.educational.dto.SaveUniversityDto;
import giasuomt.demo.educational.model.University;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/universty")
@AllArgsConstructor
public class UniversityController extends GenericController<SaveUniversityDto, University, Long, BindingResult> {

}
