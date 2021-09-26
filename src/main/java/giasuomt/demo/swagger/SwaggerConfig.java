package giasuomt.demo.swagger;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration //Để Spring nhận cấu hình mà mình cài đặt
@EnableSwagger2 //Thư viện swagger2 mà mình đã add vô pom.xml
public class SwaggerConfig {
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("giasuomt.demo"))                                   
          .build()                                    
          .apiInfo(getApiInfo());
    }
    
    private ApiInfo getApiInfo() {
    	return new ApiInfoBuilder()
    			.title("Giasuomt Application")
    			.description("Giasuomt for education")
    			.contact(new Contact("Phạm Thành Hưng","thanhhung.vl.381@gmail.com","thanhhung381"))
    			.license("MIT2")
    			.build();
    }
}
