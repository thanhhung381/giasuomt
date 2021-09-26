package giasuomt.demo.validation.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration //Để Spring nhận cấu hình mà mình cài đặt
public class ValidationConfig {
	//Cấu hình để Spring biết message source là đường dẫn tới file messages.properties
	@Bean(name = "messageSource") //Khai báo đây là 1 cái Bean - để khi Spring chạy lên thì nó biết đây là cái để inject vô những nơi cần dùng
	public MessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/validation/messages"); //Spring tính classpath là src/main/resource
		messageSource.setDefaultEncoding("UTF-8");
		
		return messageSource;
	}
	//Cấu hình để thằng Validation nó lấy tới cái message source
	@Bean(name = "validator")
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(getMessageSource());		
		return validator;
	}
}
