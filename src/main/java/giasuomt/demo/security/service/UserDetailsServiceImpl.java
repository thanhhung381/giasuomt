package giasuomt.demo.security.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
		
		//load lên username
		Optional<User> user=iUserRepository.findByUsername(username);
		//load lấy len danh sách quyền 
		
		if(!user.isPresent())
		{
			throw  new UsernameNotFoundException("Username is invalid");
		}
		
		
		List<GrantedAuthority> authorities=getAuthorities(user.get().getRoles());
		
		
		return new UserDetailsDto(username, username, authorities);
	}

	private List<GrantedAuthority> getAuthorities(List<Role> roles) {
		
		List<GrantedAuthority> authorities=new LinkedList<>();
		
		for(int i=0;i<roles.size();i++)
		{
			SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(roles.get(i).getName());
			authorities.add(simpleGrantedAuthority);
		}
		
		
		return authorities;
	}
	
	

	
	
	
}
