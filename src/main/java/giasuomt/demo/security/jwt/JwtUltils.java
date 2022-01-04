package giasuomt.demo.security.jwt;



import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.apachecommons.CommonsLog;

@Component
public class JwtUltils {
	
	@Value("${gsomt.app.jwt-duration}")
	private Long jwtDuration; //thời hạn của JWT được cấu hình ở file application.yml;
	
	@Value("${gsomt.app.jwt-secret}")
	private String secretKey; // cấu hình ở file applicaton ở file application.yml
	
	private static final Logger logger=LoggerFactory.getLogger(JwtUltils.class);
	
	
	public String generateJwtToken(Authentication authenticateAction)
	{	
		
		UserDetails userDetails=(UserDetails)authenticateAction.getPrincipal();
		
	
		
		Date date=new Date();
		
		return  Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(date)
				.setExpiration(new Date(date.getTime()+jwtDuration)) 
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();//cho phép hoạt động
	}
	
	
	//validate một cái token
	public boolean validateJWtToken(String token)
	{
		
	
			try {
				Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
				
				return true;
				
			} catch (ExpiredJwtException e) {
				logger.error("JWT Token is Experied: {}",e.getMessage()); //e.getMessage đẩy vào {}
				
			} catch (MalformedJwtException e) {
				logger.error("JWT Token is invalid: {}",e.getMessage());
			} catch (SignatureException e) {
				logger.error("JWT Token is incorrect Signature: {}",e.getMessage());
			} catch (IllegalArgumentException e) {
				logger.error("JWT Token is empty: {}",e.getMessage());
			}catch(UnsupportedJwtException e)
			{
				logger.error("JWT Token is unsupported : {}",e.getMessage());
			}
			
	
		
		
		return false;
	}
	
	
	

	public String getTokenFromRequet(HttpServletRequest request) {
		
		
		//bước 1 lấy header ra vì JWT trong header
		String header=request.getHeader("Authorization");// truyền tên header 
		//bước 2 kiểm tra có bắt đầu Bearer hay ko
		if(header!=null && header.startsWith("Bearer "))
		{
			return header.substring("Bearer ".length(),header.length());//kiểm tra xem có đúng chuẩn không
		}
	
		
		return null;
	}


	public String getUsernameToken(String token) {
		
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
			//vì trong JWT có phần body mà trong phần body đó nó có chứa subject mà subject chứa username so JWT ở trên mình đã cấu hình hihi
	}
	
}
