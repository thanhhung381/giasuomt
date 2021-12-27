package giasuomt.demo.security.dto;

import lombok.Setter;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;


public class UserDetailsDto extends User  implements UserDetails {

	public UserDetailsDto(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		
	}





	

}
