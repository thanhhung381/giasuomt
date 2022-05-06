package giasuomt.demo.tags.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.tags.dto.SaveRegisterAndLearnerTagDto;
import giasuomt.demo.tags.model.RegisterAndLearnerTag;
import giasuomt.demo.tags.model.TutorTag;
import lombok.AllArgsConstructor;

@RequestMapping("/api/registerAndLearnerTag")
@RestController
@AllArgsConstructor
public class RegisterAndLearnerTagController extends GenericController<SaveRegisterAndLearnerTagDto, RegisterAndLearnerTag, Long>{

}
