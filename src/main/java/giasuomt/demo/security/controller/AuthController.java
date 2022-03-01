package giasuomt.demo.security.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
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
	
	
	@PostMapping("/api/login")

	public ResponseEntity<Object> login(@RequestBody LoginDto dto,BindingResult bindingResult)
	{
		Authentication authentication=null;


		
		try {
			//authenticate (xác thực) theo username và password của user, và nếu thành công thì nó sẽ trả về 1 authentication
			authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));
			
			//Phải add Authentication của User vô Security Context thì khi nó check nó mới biết user này đã đăng nhập hay chưa
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			
			//return lại JWT cho người dùng
			String jwt=jwtUltils.generateJwtToken(authentication);
			
			//
//			Cookie jwtTokenCookie = new Cookie("userjwt", jwt);
//
//			jwtTokenCookie.setMaxAge(7 * 24 * 60 * 60);
//			jwtTokenCookie.setSecure(true);
//			jwtTokenCookie.setHttpOnly(true);
//			jwtTokenCookie.setPath("/");
//			response.addCookie(jwtTokenCookie);

			ResponseCookie responseCookie = ResponseCookie
	                .from("userjwt", jwt)
	                .secure(false)
	                .httpOnly(true)
	                .path("/")
	                .maxAge(7 * 24 * 60 * 60)
	                .sameSite("None")
	                .build();
			response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
			


			


		    // return response entity
		    return new ResponseEntity<>(HttpStatus.OK);
			
//			return ResponseHandler.getResponse(new JwtDto().jwt(jwt),HttpStatus.OK);
			
			
			
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		
		return ResponseHandler.getResponse("username and password is invalid",HttpStatus.BAD_REQUEST);
		
	}
	
	
}
