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
import io.netty.util.internal.SystemPropertyUtil;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
		String meo="http://localhost:8080/api/file/downloadFile/1.jpg";
		
		String[] sep=meo.split("/");
		
		
		System.out.println(sep[6]);
	}	
	

}
 