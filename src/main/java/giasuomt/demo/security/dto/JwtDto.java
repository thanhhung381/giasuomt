package giasuomt.demo.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDto {
	private String jwtString;
	
	public JwtDto jwt(String jwtString)
	{
		this.jwtString=jwtString;
		return this;
	}
}
