package giasuomt.demo.task.controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.task.dto.SaveRegistrationDto;
import giasuomt.demo.task.model.Registration;
import lombok.AllArgsConstructor;

@RequestMapping("/api/registration")
@RestController
@AllArgsConstructor
public class RegistrationController extends GenericController<SaveRegistrationDto, Registration, Long, BindingResult>{

}
