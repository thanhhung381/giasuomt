package giasuomt.demo;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import giasuomt.demo.commondata.generator.TaskCodeGenerator;
import giasuomt.demo.commondata.util.DateTimeUtils;
import io.netty.util.internal.SystemPropertyUtil;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

		
		
	 String localDateTime = DateTimeUtils.toString(LocalDateTime.now());
		

		String[] sep = localDateTime.split("-");

		String year = sep[0];

		int yearSepNow = Integer.parseInt(year);

		String month = sep[1];

		int monthSepNow = Integer.parseInt(month);

		String dateAndTime = sep[2];

		String dateArrayString[] = dateAndTime.split(" ");

		String date = dateArrayString[0];

		int daySepNow = Integer.parseInt(date);// thoi gian hien tai
		
		////
		String day="21291001";

		
		System.out.println(day.substring(4, 6));
	
	}	
	

}
 