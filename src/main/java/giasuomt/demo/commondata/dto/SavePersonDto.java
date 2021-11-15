package giasuomt.demo.commondata.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import giasuomt.demo.commondata.util.DateUtils;
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
	private String gender;

	private String birthYear;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT) // Quy định date format khi nó add đối
																					// tượng thành Json để trả về
																					// Clients
	@DateTimeFormat(pattern = DateUtils.DATE_FORMAT) // Quy định date format để lưu xuống database
	private LocalDate birthDate;

	//@NotNull
	private String phones;

	//@Email
	private String emails;

	private String zaloes;

	private String fbs;

	private String iDNo;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT) // Quy định date format khi nó add đối
																					// tượng thành Json để trả về
																					// Clients
	@DateTimeFormat(pattern = DateUtils.DATE_FORMAT) // Quy định date format để lưu xuống database
	private LocalDateTime issuedOn;
}
