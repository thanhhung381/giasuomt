package giasuomt.demo.security.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import giasuomt.demo.role.model.Role;
import giasuomt.demo.security.dto.UserDetailsDto;
import giasuomt.demo.user.model.User;
import giasuomt.demo.user.repository.IUserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private IUserRepository iUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// load lên username
		Optional<User> user = iUserRepository.findByUsername(username);
		// load lấy len danh sách quyền
		if (!user.isPresent()) {
			throw new UsernameNotFoundException("Username is invalid");
		}
		Set<GrantedAuthority> authorities = getAuthorities(user.get().getRoles());
		return new UserDetailsDto(user.get().getUsername(), user.get().getPassword(), authorities);
	} 

	private Set<GrantedAuthority> getAuthorities(Set<Role> roles) {
		Set<GrantedAuthority> authorities = new HashSet<>();
		for (Role role : roles) {
			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getName());
			authorities.add(simpleGrantedAuthority);
		}
		return authorities;
	}

}
