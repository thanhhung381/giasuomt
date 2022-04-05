package giasuomt.demo.tutorrequest.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.tutorrequest.dto.SaveTutorRequestDto;
import giasuomt.demo.tutorrequest.model.TutorRequest;
import lombok.AllArgsConstructor;

@RequestMapping("/api/tutorRequest")
@RestController
@AllArgsConstructor
public class TutorRequestController extends GenericController<SaveTutorRequestDto, TutorRequest, Long, BindingResult> {

}
