package giasuomt.demo.user.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.uploadfile.service.IAvatarService;
import giasuomt.demo.user.dto.SaveUserDto;
import giasuomt.demo.user.model.User;
import lombok.AllArgsConstructor;

@RequestMapping("/api/createUser")
@RestController
@AllArgsConstructor
public class UserController extends GenericController<SaveUserDto, User, Long, BindingResult> {

}
