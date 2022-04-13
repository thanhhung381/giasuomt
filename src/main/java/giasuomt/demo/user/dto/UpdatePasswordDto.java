package giasuomt.demo.user.dto;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
public class UpdatePasswordDto {
	
	private String username;
	
	private String token;
	
	private String password;
	
	private String confirmPassword;
}
