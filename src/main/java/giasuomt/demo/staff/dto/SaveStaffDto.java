package giasuomt.demo.staff.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import giasuomt.demo.commondata.util.DateUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveStaffDto {
	
	
	private Long id;
	
	private Long idAvatar;
	
	private String registeredStatus;

	// THÔNG TIN CÁ NHÂN
	// @NotBlank
	private String fullName;
	
	

	// @NotNull //kiểu Enum mình ko nên để @NotBlank mà nên để @NotNull
	private String gender;

	private String birthYear;

	
	private LocalDate birthDate;

	private String phones;

	private String emails;

	private String zaloes;

	private String fbs;

	private String idCardNumber;

	
	private LocalDateTime idCardIssuedOn;
}
