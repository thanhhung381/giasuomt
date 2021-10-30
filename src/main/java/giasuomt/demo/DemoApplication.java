package giasuomt.demo;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import giasuomt.demo.commondata.util.DateTimeUtils;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
//	public static void main(String[] args) {
//		/*
//		 * SpringApplication.run(DemoApplication.class, args); String localDateTime =
//		 * DateTimeUtils.toString(LocalDateTime.now());
//		 * System.out.println(localDateTime);
//		 * 
//		 * String[] sep = localDateTime.split("-");
//		 * 
//		 * String year = sep[0];
//		 * 
//		 * String month = sep[1];
//		 * 
//		 * String dateAndTime=sep[2];
//		 * 
//		 * String dateArrayString[]=dateAndTime.split(" ");
//		 * 
//		 * String date=dateArrayString[0];
//		 * 
//		 * 
//		 * 
//		 * 
//		 * 
//		 * System.out.println(year.substring(2,4));
//		 */
//
//		
//
//	}
	
	

}
