package giasuomt.demo.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
	
	private String usernameOrPhonesOrEmail;
	
	private String password;
	
}
