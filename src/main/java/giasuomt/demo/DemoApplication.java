package giasuomt.demo;

import java.nio.file.Path;

import java.nio.file.Paths;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.aspectj.apache.bcel.classfile.Code;
import org.joda.time.DateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.util.StringUtils;

import giasuomt.demo.commondata.generator.TaskCodeGenerator;
import giasuomt.demo.commondata.generic.StringUltilsForAreaID;
import giasuomt.demo.commondata.util.DateTimeUtils;
import giasuomt.demo.location.model.TaskPlaceAddress;
import giasuomt.demo.person.Ultils.UpdateSubjectGroupMaybeAndSure;
import giasuomt.demo.token.service.TokenService;

@SpringBootApplication

public class DemoApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}
	

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
		
		Set<String> a=new HashSet<>();
		
		
		a.add("a");
		
		a.add("b");
		
		a.add("c");
		
		a.add("a");
		
		a.add("b");
		
		
		
		
		System.out.println(a.toString());

		
      
	}

}
