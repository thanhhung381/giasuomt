package giasuomt.demo.commondata.model;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import giasuomt.demo.commondata.util.DateUtils;
import giasuomt.demo.commondata.util.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass // để các lớp con có thể kế thừa lại được các annotation của lớp này
@EntityListeners(AuditingEntityListener.class) // để model này lấy được @EnableJpaAuditing đã Config ở bên // JpaConfig.java để @CreatedDate,@LastModifiedDate có hiệu lực
public class Person extends AbstractEntityNotId {
//	@Size(min = 3, max = 50, message = "{user.username.size}")
//	@Column(unique = true) // để các giá trị username ko được trùng nhau
	private String registeredStatus;
	// THÔNG TIN CÁ NHÂN
	// @NotBlank
	private String fullName;
	private String englishFullName;
	// @NotNull //kiểu Enum mình ko nên để @NotBlank mà nên để @NotNull
	@Enumerated(EnumType.STRING)
	private Gender gender;
	private String birthYear;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT) // Quy định date format khi nó add đối																				// tượng thành Json để trả về																					// Clients
	@DateTimeFormat(pattern = DateUtils.DATE_FORMAT) // Quy định date format để lưu xuống database
	private LocalDate birthDate;
	private String phones;
	private String emails;
	private String zaloes;
	private String fbs;
	private String idCardNumber;
	private String idCardIssuedOn;
}
