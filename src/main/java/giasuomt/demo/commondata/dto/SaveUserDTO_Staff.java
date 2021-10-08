package giasuomt.demo.commondata.dto;
import java.time.LocalDate;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import giasuomt.demo.commondata.util.DateUtils;

@MappedSuperclass
public class SaveUserDTO_Staff {
//	@NotBlank(message = "{role.name.notblank}")
//	@Length(min = 4, max = 50, message = "{role.description.size}")
	private String fullName;
	
	private String gender;
	
	private String phones;
	
	private String zaloes;
	
	private String emails;
	
	private String fbs;
	
	@JsonDeserialize //Để deserialize từ dạng string của JSON về dạng LocalDate 
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT) //Quy định date format khi nó add đối tượng thành Json để trả về Clients
	@DateTimeFormat(pattern = DateUtils.DATE_FORMAT) //Quy định date format để lưu xuống database
	private LocalDate birthDate;
	
	private String birthYear;	
	


	
//	@NotBlank(message = "{role.description.notblank}")
//	private String description;
	
	
	
	public String getFullName() {
		return fullName;
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

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public String getFbs() {
		return fbs;
	}

	public void setFbs(String fbs) {
		this.fbs = fbs;
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
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
