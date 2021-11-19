package giasuomt.demo.person.controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.person.dto.SaveRegisterAndLearnerDto;
import giasuomt.demo.person.model.RegisterAndLearner;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/registerAndLearner")
@AllArgsConstructor
public class RegisterAndLearnerController extends GenericController<SaveRegisterAndLearnerDto, RegisterAndLearner, Long, BindingResult> {
	
}
