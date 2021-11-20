package giasuomt.demo.commondata.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import giasuomt.demo.commondata.util.DateUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass // để các lớp con có thể kế thừa lại được các annotation của lớp này
@EntityListeners(AuditingEntityListener.class) // để model này lấy được @EnableJpaAuditing đã Config ở bên // JpaConfig.java để @CreatedDate,@LastModifiedDate có hiệu lực
public class Person extends AbstractEntity {
//	@Size(min = 3, max = 50, message = "{user.username.size}")
//	@Column(unique = true) // để các giá trị username ko được trùng nhau
	private String username;

	private String password;

	private String registeredStatus;

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

	private String phones;

	private String emails;

	private String zaloes;

	private String fbs;

	private String idCardNumber;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT) // Quy định date format khi nó add đối
																					// tượng thành Json để trả về
																					// Clients
	@DateTimeFormat(pattern = DateUtils.DATE_FORMAT) // Quy định date format để lưu xuống database
	private LocalDateTime idCardIssuedOn;
}
