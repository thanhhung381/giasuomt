package giasuomt.demo.role.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.role.dto.SaveRoleDto;
import giasuomt.demo.role.model.Role;
import lombok.AllArgsConstructor;

@RequestMapping("/api/role")
@RestController
@AllArgsConstructor
public class RoleController extends GenericController<SaveRoleDto,Role, Long, BindingResult> {

}
