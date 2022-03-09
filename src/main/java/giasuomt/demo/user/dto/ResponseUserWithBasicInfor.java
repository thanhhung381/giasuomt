package giasuomt.demo.user.dto;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
public class ResponseUserWithBasicInfor {
    
	private Long id;
 	
	private String username;

	private String email;

	private String phones;
	
	private String avatar;
}
