package giasuomt.demo;

import java.nio.file.Path;

import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import giasuomt.demo.commondata.generator.TaskCodeGenerator;
import giasuomt.demo.commondata.util.DateTimeUtils;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
		
		String a="423421341234#";
		
		System.out.println(a.substring(a.indexOf('#')-4,a.indexOf('#')));
		
	}	
	

}
 