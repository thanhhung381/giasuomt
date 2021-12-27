package giasuomt.demo.security.jwt;



import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.apachecommons.CommonsLog;

@Component
public class JwtUltils {
	
	@Value("${gsomt.app.jwt-duration}")
	private Long jwtDuration; //thời hạn của JWT được cấu hình ở file application.yml;
	
	@Value("${gsomt.app.jwt-secret}")
	private String secretKey; // cấu hình ở file applicaton ở file application.yml
	
	
	public String generateJwtToken(Authentication authenticateAction)
	{	
		
		UserDetails userDetails=(UserDetails)authenticateAction.getPrincipal();
		
		System.out.println(userDetails.getPassword());
		
		Date date=new Date();
		
		return  Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(date)
				.setExpiration(new Date(date.getTime()+jwtDuration)) 
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();//cho phép hoạt động
	}
}
