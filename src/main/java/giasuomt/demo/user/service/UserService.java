package giasuomt.demo.user.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.person.repository.IRegisterAndLearnerRepository;
import giasuomt.demo.person.repository.ITutorRepository;
import giasuomt.demo.role.model.Role;
import giasuomt.demo.role.repository.IRoleRepository;
import giasuomt.demo.user.dto.SaveUserDto;
import giasuomt.demo.user.dto.UpdateRegisterAndLearnerForUser;
import giasuomt.demo.user.dto.UpdateTutorForUser;
import giasuomt.demo.user.model.User;
import giasuomt.demo.user.repository.IUserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService extends GenericService<SaveUserDto, User, Long> implements IUserService {

	private IUserRepository iUserRepository;

	private MapDtoToModel mapDtoToModel;

	private PasswordEncoder passwordEncoder;

	private ITutorRepository iTutorRepository;

	private IRoleRepository iRoleRepository;

	private IRegisterAndLearnerRepository iRegisterAndLearnerRepository;

	public User create(SaveUserDto dto) {
		User user = new User();
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

		List<Role> roleList = user.getRoles();
		if (dto.getIdUser() != 0 && dto.getIdUser() > 0) // id=0 delete all Role
		{

			user.setTutor(iTutorRepository.getOne(dto.getIdUser()));
			roleList.add(role);
			user.setRoles(roleList);
		} else {

			for (int i = 0; i < roleList.size(); i++) {

				if (roleList.get(i).getName().contains("tutor-role")) {
					user.removeTutor(roleList.get(i));
					i--;
				}
			}
			
			user.setTutor(null);
		}

		return iUserRepository.save(user);
	}

	public User updateRegisterAndLearnerForUser(UpdateRegisterAndLearnerForUser dto) {
		User user = iUserRepository.getOne(dto.getId());

		Role role = iRoleRepository.findByRoleNameBy("register-and-learner-role");

		List<Role> roleList = user.getRoles();

		if (dto.getIdRegisterAndLearner() != 0 && dto.getIdRegisterAndLearner() > 0) // id=0 delete all Role
		{

			user.setRegisterAndLearner(iRegisterAndLearnerRepository.getOne(dto.getIdRegisterAndLearner()));

			roleList.add(role);

			user.setRoles(roleList);
		} else {

			for (int i = 0; i < roleList.size(); i++) {
				if (roleList.get(i).getName().contains("register-and-learner-role")) {
					user.removeTutor(roleList.get(i));
					i--;
				}
				user.setRegisterAndLearner(null);
			}
		}
		return iUserRepository.save(user);
	}

}
