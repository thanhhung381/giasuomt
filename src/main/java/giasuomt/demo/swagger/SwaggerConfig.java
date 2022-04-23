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
          .securitySchemes(Arrays.asList(apiKey())) //để đặt được cái apiKey của thằng swagger
          .securityContexts(Arrays.asList(securityContext())) //nhận được cái JWT 
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
    //api key đẽ cho mình nhập jwt dô và xuất hiện cái nút Authorization
    private ApiKey apiKey()
    {
    	return new ApiKey("JWT", "Authorization", "header"); //header truyền vào cookie vào passAs truyền vào header có tên là Authorization
    }
    
    // khởi tạo
    private SecurityContext securityContext()
    {
    	return SecurityContext.builder()
    			.securityReferences(securityReferences()).build();            // builder(): ko tạo instance liền mà bắt mình nhập thông tin trước xong sau đó mới tạo 
    }
    
    private List<SecurityReference> securityReferences()
    {
    	AuthorizationScope authorizationScope=new AuthorizationScope("global", "All application can access"); //
    	
    	AuthorizationScope[] authorizationScopes=new AuthorizationScope[1];
    	
    	authorizationScopes[0]=authorizationScope;
    	
    	return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); //string refference là tham chiếu tới apiKey nào
    }
    
}
