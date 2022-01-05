package giasuomt.demo.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import ch.qos.logback.core.joran.spi.DefaultClass;
import lombok.AllArgsConstructor;
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUltils jwtUltils;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private Logger logger=LoggerFactory.getLogger(JwtAuthorizationFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//bước 1 lấy token ra từ request
		String token =jwtUltils.getTokenFromRequet(request);
		
		//bước 2 kiểm tra neesu đúng sẽ lấy user name có token đó ra
		if(token!=null && jwtUltils.validateJWtToken(token))
		{
			String username=jwtUltils.getUsernameToken(token);    
			
			try {
				UserDetails userDetails=userDetailsService.loadUserByUsername(username);// lấy ra được username rồi xong kiểm tra tiếp
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
				
				=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} catch (UsernameNotFoundException e) {
				
				logger.error("An anonymous user has been connected to server From ",request.getRemoteAddr());
			}
			
		}
		
		filterChain.doFilter(request, response);
		
	}

	

}
