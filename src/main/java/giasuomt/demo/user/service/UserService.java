package giasuomt.demo.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;

import giasuomt.demo.user.dto.SaveUserDto;
import giasuomt.demo.user.model.User;
import giasuomt.demo.user.repository.IUserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService extends GenericService<SaveUserDto, User, Long> implements IUserService {

	private IUserRepository iUserRepository;

	private MapDtoToModel mapDtoToModel;

	private PasswordEncoder passwordEncoder;

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

}
