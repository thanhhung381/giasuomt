package giasuomt.demo;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import giasuomt.demo.commondata.util.DateTimeUtils;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

		String k="21271001";
		
		System.out.println(k.substring(6, 8));
	}	
	

}
