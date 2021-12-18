package giasuomt.demo;

import java.nio.file.Path;


import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import giasuomt.demo.commondata.generator.TaskCodeGenerator;
import giasuomt.demo.commondata.generic.StringUltilsForAreaID;
import giasuomt.demo.commondata.util.DateTimeUtils;
import giasuomt.demo.location.model.TaskPlaceAddress;
import giasuomt.demo.person.Ultils.UpdateRegisterdSubjectAndCreateAppilcation;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
		
		List<String> khang=new LinkedList<>();
		
		khang.add("a");
		
		khang.add("b");
		
		khang.add("c");
		
		khang.add("d");
		
		khang.add("a"); 
		
		khang.add("a"); 
		
		khang.add("d");
		
		
		
		System.out.println("khi chưa sử dụng "+khang.toString());
		
		System.out.println("hàm sử dụng "+UpdateRegisterdSubjectAndCreateAppilcation.removeDuplicateElenmet(khang).toString());
		
	}
 
}
 