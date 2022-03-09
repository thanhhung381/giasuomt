package giasuomt.demo.user.service;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amazonaws.SdkClientException;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.person.repository.IRegisterAndLearnerRepository;
import giasuomt.demo.person.repository.ITutorRepository;
import giasuomt.demo.role.model.Role;
import giasuomt.demo.role.repository.IRoleRepository;
import giasuomt.demo.staff.model.Staff;
import giasuomt.demo.uploadfile.repository.IAvatarAwsRepository;
import giasuomt.demo.uploadfile.service.IAvatarAwsService;
import giasuomt.demo.uploadfile.ultils.AwsClientS3;
import giasuomt.demo.user.dto.SaveUserDto;
import giasuomt.demo.user.dto.UpdateRegisterAndLearnerForUser;
import giasuomt.demo.user.dto.UpdateAndDeleteRoleForUser;
import giasuomt.demo.user.dto.UpdateAvatarUser;
import giasuomt.demo.user.dto.UpdateTutorForUser;
import giasuomt.demo.user.model.User;
import giasuomt.demo.user.repository.IUserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class UserService extends GenericService<SaveUserDto, User, Long> implements IUserService {

	private IUserRepository iUserRepository;

	private AwsClientS3 awsClientS3;

	private MapDtoToModel mapDtoToModel;

	private PasswordEncoder passwordEncoder;

	private ITutorRepository iTutorRepository;

	private IRoleRepository iRoleRepository;

	private IRegisterAndLearnerRepository iRegisterAndLearnerRepository;

	private IAvatarAwsService iAvatarAwsService;
	
	private IAvatarAwsRepository iAvatarAwsRepository;

	public User create(SaveUserDto dto) {
		User user = new User();

		if (findAll().isEmpty()) {

			// nếu rỗng tạo admin-role
			String nameRole = "admin-role";

			Role role = new Role().addRoleName(nameRole);

			iRoleRepository.save(role);

			List<Role> roles = user.getRoles();
			Role roleAdmin = iRoleRepository.findByRoleNameBy(nameRole);
			roles.add(roleAdmin);

			user.setRoles(roles);
			return save(user, dto);
		}

		return save(user, dto);
	}

	public User save(User user, SaveUserDto dto) {
		try {

			mapDto(dto, user);

			return iUserRepository.save(user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void mapDto(SaveUserDto dto, User user) {
		user = (User) mapDtoToModel.map(dto, user);
		user.setPassword(passwordEncoder.encode(dto.getPassword()));

	}

	@Override
	public User update(SaveUserDto dto) {

		User user = iUserRepository.getOne(dto.getId());
		return save(user, dto);
	}

	@Override
	public User updateTutorForUser(UpdateTutorForUser dto) {
		User user = iUserRepository.getOne(dto.getId());

		Role role = iRoleRepository.findByRoleNameBy("tutor-role");

		try {
			List<Role> roleList = user.getRoles();
			if (dto.getIdTutor() != 0 && dto.getIdTutor() > 0) // id=0 delete all Role
			{

				user.setTutor(iTutorRepository.getOne(dto.getIdTutor()));
				roleList.add(role);
				user.setRoles(roleList);
			} else {

				for (int i = 0; i < roleList.size(); i++) {

					if (roleList.get(i).getName().contains("tutor-role")) {
						user.removeRole(role);
						i--;
					}
				}

				user.setTutor(null);
			}

			return iUserRepository.save(user);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public User updateRegisterAndLearnerForUser(UpdateRegisterAndLearnerForUser dto) {
		User user = iUserRepository.getOne(dto.getId());

		Role role = iRoleRepository.findByRoleNameBy("register-and-learner-role");

		try {
			List<Role> roleList = user.getRoles();

			if (dto.getIdRegisterAndLearner() != 0 && dto.getIdRegisterAndLearner() > 0) // id=0 delete all Role
			{

				user.setRegisterAndLearner(iRegisterAndLearnerRepository.getOne(dto.getIdRegisterAndLearner()));

				roleList.add(role);

				user.setRoles(roleList);
			} else {

				for (int i = 0; i < roleList.size(); i++) {
					if (roleList.get(i).getName().contains("register-and-learner-role")) {
						user.removeRole(role);
						i--;
					}
					user.setRegisterAndLearner(null);
				}
			}
			return iUserRepository.save(user);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User updateRoleForUser(UpdateAndDeleteRoleForUser dto) {
		User user = iUserRepository.getOne(dto.getId());

		List<Long> listRoleId = dto.getIdRole();

		List<Role> roles = user.getRoles();

		try {

			for (int i = 0; i < listRoleId.size(); i++) {
				Role role = iRoleRepository.getOne(listRoleId.get(i));
				roles.add(role);
			}
			user.setRoles(roles);

			return iUserRepository.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public User deleteRoleForUser(UpdateAndDeleteRoleForUser dto) {

		User user = iUserRepository.getOne(dto.getId());

		List<Long> listRoleId = dto.getIdRole();

		List<Role> roles = user.getRoles();

		try {

			for (int i = 0; i < listRoleId.size(); i++) {
				for (int j = 0; j < roles.size(); j++) {
					Role role = iRoleRepository.getOne(listRoleId.get(i));
					if (role.getId() == roles.get(j).getId()) {
						user.removeRole(roles.get(j));
						j--;
					}
				}
			}
			user.setRoles(roles);

			return iUserRepository.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean findByJWT(String JWT) {
		if (iUserRepository.findByUsername(JWT).isEmpty())
			return false;
		return true;

	}

	@Override
	public User updateAvartarUser(UpdateAvatarUser dto) {
		try {
			User user = iUserRepository.getOne(dto.getId());

			String avatarURL = user.getAvatar();

			if ( avatarURL == null) {
				user.setAvatar(iAvatarAwsRepository.getById(dto.getIdAvatar()).getUrlAvatar());
			}
			else
			{
				
				
				
				
				
				iAvatarAwsRepository.deleteBysUrlAvatar(avatarURL);
				
				awsClientS3.getAmazonS3().deleteObject("avatargsomt", avatarURL.substring(avatarURL.lastIndexOf('/') + 1));
				
				user.setAvatar(iAvatarAwsRepository.getById(dto.getIdAvatar()).getUrlAvatar());

				
				
				
			}

			user = iUserRepository.save(user);

			return iUserRepository.save(user);
		} catch (SdkClientException e) {

			e.printStackTrace();
		}
		return null;
	}

}
