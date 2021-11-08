package giasuomt.demo.security.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;


@Configuration  //để nó biết đây là 1 file config
@EnableWebSecurity  //để nó biết đây là config cho Spring Security
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override //Mình Override lại thằng configure(HttpSecurity http) (có sẵn trong Spring Security) -> để mình config việc nó security trên các cái Http Request của mình.
	protected void configure(HttpSecurity http) throws Exception {
		//Enable CORS - xem lại tài liệu để hiểu CORS là gì - nói ngắn gọn là ở đây mình cấu hình để cho phép nguồn nào (domain nào) có thể gửi HttpRequest tới Server của mình được, và có thể gởi Request tới API nào được, API nào không.
//		http.cors();  //enable filter cors
//		http.csrf().disable();  //disable csrf tạm thời để mình dev
	    http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()); //enable cors

	}
}
