package giasuomt.demo.role.service;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.role.dto.SaveRoleDto;
import giasuomt.demo.role.model.Role;
import giasuomt.demo.user.dto.SaveUserDto;

public interface IRoleService extends IGenericService<SaveRoleDto, Role, Long> {

}
