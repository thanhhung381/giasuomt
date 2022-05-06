package giasuomt.demo.tags.controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.tags.dto.SaveTutorTagDto;
import giasuomt.demo.tags.model.TutorTag;
import lombok.AllArgsConstructor;

@RequestMapping("/api/tutorTag")
@RestController
@AllArgsConstructor
public class TutorTagController extends GenericController<SaveTutorTagDto, TutorTag, String>{

}
