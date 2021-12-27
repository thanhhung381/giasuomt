package giasuomt.demo.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.security.dto.JwtDto;
import giasuomt.demo.security.dto.LoginDto;
import giasuomt.demo.security.jwt.JwtUltils;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthController {

	
	private AuthenticationManager authenticationManager;
		//kiểm tra đăng nhập
	
	private JwtUltils jwtUltils;
	
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginDto dto,BindingResult bindingResult)
	{
		Authentication authentication=null;
		
		try {
			
			authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			
			
			String jwt=jwtUltils.generateJwtToken(authentication);
			
			return ResponseHandler.getResponse(new JwtDto().jwt(jwt),HttpStatus.OK);
			
			
			
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		
		return ResponseHandler.getResponse("username and password is invalid",HttpStatus.OK);
		
	}
	
	
}
