package giasuomt.demo.commondata.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import giasuomt.demo.commondata.util.DateUtils;
import giasuomt.demo.commondata.util.UserGender;
import giasuomt.demo.commondata.util.UserStatus;

@MappedSuperclass
public class User extends AbstractEntity {
	@Size(min = 3, max = 50, message = "{user.username.size}")
	@Column(unique = true) //để các giá trị username ko được trùng nhau
	private String username;
	
	private String password;
	
//	@NotNull  //kiểu Enum mình ko nên để @NotBlank mà nên để @NotNull
	@Enumerated(EnumType.STRING) //phải dùng Enumerated để database biết là nó phải tạo cái cột kiểu là gì (vd nếu Enum mình để là số thì type là Interger, ở đây Enum mình là chữ nên Type là STRING)
	private UserStatus status; //UserStatus là 1 enum (xem trong UserStatus.java) //enum giống như là 1 kiểu dữ liệu do mình tự định nghĩa nhưng nó gọn hơn Object
	
//	@Email
//	@ElementCollection(targetClass=String.class)
	private String emails;
	
//	@NotNull
//	@ElementCollection(targetClass=String.class)
	private String phones;
	
	private String zaloes;
	
	private String fbs;
	
	private String avatars;
	
//	@NotBlank
	private String fullName;
	
//	@NotNull  //kiểu Enum mình ko nên để @NotBlank mà nên để @NotNull
	@Enumerated(EnumType.STRING) //phải dùng Enumerated để database biết là nó phải tạo cái cột kiểu là gì (vd nếu Enum mình để là số thì type là Interger, ở đây Enum mình là chữ nên Type là STRING)
	private UserGender gender;	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT) //Quy định date format khi nó add đối tượng thành Json để trả về Clients
	@DateTimeFormat(pattern = DateUtils.DATE_FORMAT) //Quy định date format để lưu xuống database
	private LocalDate birthDate;
	
	private String birthYear;
	

	//getter,setter kiểu fluentAPI
	public User username(String username) {
		this.username = username;
		return this;
	}
	public User status(UserStatus status) {
		this.status = status;
		return this;
	}
	public User emails(String emails) {
		this.emails = emails;
		return this;
	}
	public User phones(String phones) {
		this.phones = phones;
		return this;
	}
	public User zaloes(String zaloes) {
		this.zaloes = zaloes;
		return this;
	}
	public User fbs(String fbs) {
		this.fbs = fbs;
		return this;
	}
	public User avatars(String avatars) {
		this.avatars = avatars;
		return this;
	}
	public User fullName(String fullName) {
		this.fullName = fullName;
		return this;
	}
	public User gender(UserGender gender) {
		this.gender = gender;
		return this;
	}
	public User birthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
		return this;
	}
	public User birthYear(String birthYear) {
		this.birthYear = birthYear;
		return this;
	}
	
	
	//getter, setter
	public String getUsername() {
		return username;
	}

	public String getEmails() {
		return emails;
	}
	public void setEmails(String emails) {
		this.emails = emails;
	}
	public String getPhones() {
		return phones;
	}
	public void setPhones(String phones) {
		this.phones = phones;
	}
	public String getZaloes() {
		return zaloes;
	}
	public void setZaloes(String zaloes) {
		this.zaloes = zaloes;
	}
	public String getFbs() {
		return fbs;
	}
	public void setFbs(String fbs) {
		this.fbs = fbs;
	}
	public String getAvatars() {
		return avatars;
	}
	public void setAvatars(String avatars) {
		this.avatars = avatars;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public UserGender getGender() {
		return gender;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}


}
