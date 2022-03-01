package giasuomt.demo.security.jwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
	    response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
	    response.setHeader("Access-Control-Allow-Credentials", "true");
	    
	    System.out.println("xxx111");
	    System.out.println(request.getCookies());
	    
		String token = null;
		
//		if(request.getCookies() != null){
			
			Optional<Cookie> cookie = Stream.of(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
		            .filter(c -> "userjwt".equals(c.getName()))
		            .findFirst();
			System.out.println(cookie);	
			token = cookie
		            .map(Cookie::getValue)
		            .orElse(null);
			
			System.out.println(token);	
//		}

//		Optional<Cookie> cookie = Stream.of(Optional.ofNullable(httpServletRequest.getCookies()))

		//bước 1 lấy token ra từ request
//		token =jwtUltils.getTokenFromRequet(request);
		
		//bước 2 kiểm tra neesu đúng sẽ lấy user name có token đó ra
		if(token!=null && jwtUltils.validateJWtToken(token))
		{
			String username=jwtUltils.getUsernameToken(token);    
			
			try {
				UserDetails userDetails=userDetailsService.loadUserByUsername(username);//Lấy toàn bộ Authorities (phân quyền) của user thông qua username 
				
				//Nếu có username thì mình tiến hành đi tạo 1 cái authentication rồi add vô context thì lần sau nó check trong context để biết user này đã đăng nhập hay chưa
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			} catch (UsernameNotFoundException e) {
				//Nếu catch đc lỗi Username Not Found thì log ra console log
				//logger được khai báo ở trên
				logger.error("An anonymous user has been connected to server from {}", request.getRemoteAddr());
			}
			
		}
		
		filterChain.doFilter(request, response);
		
	}

	

}
