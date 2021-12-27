package giasuomt.demo.user.service;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.user.dto.SaveUserDto;
import giasuomt.demo.user.model.User;

public interface IUserService extends IGenericService<SaveUserDto, User, Long> {

}
