package giasuomt.demo.commondata.dto;

import javax.persistence.MappedSuperclass;

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
	// @NotNull
	private String phones;
	// @Email
	private String emails;
	private String zaloes;
	private String fbs;
	private String idCardNumber;
	private String idCardIssuedOn;
}
