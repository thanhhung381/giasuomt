package giasuomt.demo.security.config;
import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import giasuomt.demo.security.jwt.JwtAuthorizationFilter;
import lombok.AllArgsConstructor;


@Configuration  //để nó biết đây là 1 file config
@EnableWebSecurity  //để nó biết đây là config cho Spring Security
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = false,jsr250Enabled = false)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private UserDetailsService userDetailsService;
	
	
	private JwtAuthorizationFilter jwtAuthorizationFilter;
	
	@Bean
	public PasswordEncoder getPassword()
	{
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(getPassword());
		
		Authentication authentication;
		
		
		
	}
	

	
	@Override //Mình Override lại thằng configure(HttpSecurity http) (có sẵn trong Spring Security) -> để mình config việc nó security trên các cái Http Request của mình.
	protected void configure(HttpSecurity http) throws Exception {
		//Enable CORS - xem lại tài liệu để hiểu CORS là gì - nói ngắn gọn là ở đây mình cấu hình để cho phép nguồn nào (domain nào) có thể gửi HttpRequest tới Server của mình được, và có thể gởi Request tới API nào được, API nào không.
		http.cors();  //enable filter cors
		http.csrf().disable();  //disable csrf tạm thời để mình dev //để ko bị báo lỗi 403 với các api create
	    http.cors().configurationSource(request -> { //enable cors
	    	CorsConfiguration cors = new CorsConfiguration();
	    	cors.applyPermitDefaultValues();
	    	
	        //cors.setAllowedOrigins(List.of("*"));
	    	
	    	ArrayList<String> methodList = new ArrayList<String>();
	    	methodList.add("GET");
	    	methodList.add("POST");
	    	methodList.add("PUT");
	    	methodList.add("DELETE");
	        cors.setAllowedMethods(methodList); //để cho phép các methods này
	        
	    	ArrayList<String> headerList = new ArrayList<String>();
	    	headerList.add("Access-Control-Allow-Origin");
	    	
	        return cors;
	      }); //enable cors

	    
		//config authentication (xác thực) cho các API. Chứ chưa làm đến việc authorization (phân quyền) cho các API.
		http.antMatcher("/api/**").authorizeRequests() 
//			.antMatchers("/swagger-ui.html").authenticated() //permitAll (cho phép) tất cả các truy cập vô trang swagger
//			.antMatchers("/api/**").permitAll() //permitAll (cho phép) tất cả các truy cập vô tất cả các API
			.antMatchers("/api/createUser/create").permitAll()
			.antMatchers("/api/login").permitAll()  //anthenticated (bảo mật) cho để user phải đăng nhập mới vô đc các API role
			.antMatchers("api/createUser/findByJWT").permitAll() 
			.anyRequest().authenticated(); //anthenticated (bảo mật) tất cả các request còn lại - để tránh việc kẻ xấu vô khám phá xem mình còn những trang gì nữa ko...
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//add jwt filter 
		http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
}
