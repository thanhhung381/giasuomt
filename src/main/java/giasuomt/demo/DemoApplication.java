package giasuomt.demo;

import java.nio.file.Path;


import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import giasuomt.demo.commondata.generator.TaskCodeGenerator;
import giasuomt.demo.commondata.generic.StringUltilsForAreaID;
import giasuomt.demo.commondata.util.DateTimeUtils;
import giasuomt.demo.location.model.TaskPlaceAddress;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
		
		
		
		
	}	
	
 
}
 