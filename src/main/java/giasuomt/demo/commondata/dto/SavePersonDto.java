package giasuomt.demo.commondata.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import giasuomt.demo.commondata.util.DateUtils;
import giasuomt.demo.commondata.util.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class SavePersonDto {
	private Long id;
	
	// THÔNG TIN CÁ NHÂN
	// @NotBlank
	private String fullName;

	// @NotNull //kiểu Enum mình ko nên để @NotBlank mà nên để @NotNull
	private Gender gender;

	private String birthYear;



	//@NotNull
	private String phones;

	//@Email
	private String emails;

	private String zaloes;

	private String fbs;

	private String idCardNumber;


	private LocalDateTime idCardIssuedOn;
}
