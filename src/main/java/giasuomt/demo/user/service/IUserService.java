package giasuomt.demo.user.service;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.user.dto.SaveUserDto;
import giasuomt.demo.user.dto.UpdateRegisterAndLearnerForUser;
import giasuomt.demo.user.dto.UpdateAndDeleteRoleForUser;
import giasuomt.demo.user.dto.UpdateTutorForUser;
import giasuomt.demo.user.model.User;

public interface IUserService extends IGenericService<SaveUserDto, User, Long> {
	User updateTutorForUser(UpdateTutorForUser dto);
	User updateRegisterAndLearnerForUser(UpdateRegisterAndLearnerForUser dto);
	User updateRoleForUser(UpdateAndDeleteRoleForUser dto);
	User deleteRoleForUser(UpdateAndDeleteRoleForUser dto);
	
     boolean findByJWT(String JWT);
}
