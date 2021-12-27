package giasuomt.demo.user.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import giasuomt.demo.commondata.util.DateUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveUserDto {
	
	private Long id;
	
	private String username;

	private String password;

	private String email;

	private String phones;
}
