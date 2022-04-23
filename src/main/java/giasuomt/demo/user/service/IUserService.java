package giasuomt.demo.user.service;

import java.util.Optional;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.user.dto.SaveUserDto;
import giasuomt.demo.user.dto.UpdateRegisterAndLearnerForUser;
import giasuomt.demo.user.dto.UpdateAndDeleteRoleForUser;
import giasuomt.demo.user.dto.UpdateAvatarUser;
import giasuomt.demo.user.dto.UpdatePasswordDto;
import giasuomt.demo.user.dto.UpdateTutorForUser;
import giasuomt.demo.user.model.User;

public interface IUserService extends IGenericService<SaveUserDto, User, Long> {
	public User updateTutorForUser(UpdateTutorForUser dto);

	public User updateRegisterAndLearnerForUser(UpdateRegisterAndLearnerForUser dto);

	public User updateRoleForUser(UpdateAndDeleteRoleForUser dto);

	public User deleteRoleForUser(UpdateAndDeleteRoleForUser dto);

	public boolean findByJWT(String JWT);

	public User updateAvartarUser(UpdateAvatarUser dto);

	public User findByEmail(String email);

	public User updatePassword(UpdatePasswordDto dto);

	public boolean isExistsOTP(String token, String username);

	public String findByParameters(String parameter);
}
