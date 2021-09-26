package giasuomt.demo.tutor.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.commondata.util.DateTimeUtils;
import giasuomt.demo.institution.model.Institution;
import giasuomt.demo.institution.model.Major;

@Entity
@Table(name = "student")
public class Student extends AbstractEntity {
	@Id //Quy định khoá chính
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Sinh ra các giá trị là độc lập cho các model khác nhau
	@Column(updatable = false) //Column này ko update được
	private Long id; //(khai báo là protected để các lớp con có thể truy cập vô để kế thừa)
	
	@ElementCollection(targetClass=String.class)
	private Set<String> confirmImg;
	
	private String nowLevel;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATE_TIME_FORMAT) //Quy định date format khi nó add đối tượng thành Json để trả về Clients
	@DateTimeFormat(pattern = DateTimeUtils.DATE_TIME_FORMAT) //Quy định date format để lưu xuống database
	private LocalDateTime nowLevelUpdatedAt;
	
	@ManyToOne
	@JoinColumn(name = "university_id")
	private Institution institution;
	
	@ManyToOne
	@JoinColumn(name = "major_id")
	private Major major;
	
	private String anotherMajor;
	
	@ManyToOne
	@JoinColumn(name = "tutor_id")
	private Tutor tutor;

	
	//getter,setter
	
	public Long getId() {
		return id;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<String> getConfirmImg() {
		return confirmImg;
	}

	public void setConfirmImg(Set<String> confirmImg) {
		this.confirmImg = confirmImg;
	}

	public String getNowLevel() {
		return nowLevel;
	}

	public void setNowLevel(String nowLevel) {
		this.nowLevel = nowLevel;
	}

	public LocalDateTime getNowLevelUpdatedAt() {
		return nowLevelUpdatedAt;
	}

	public void setNowLevelUpdatedAt(LocalDateTime nowLevelUpdatedAt) {
		this.nowLevelUpdatedAt = nowLevelUpdatedAt;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public String getAnotherMajor() {
		return anotherMajor;
	}

	public void setAnotherMajor(String anotherMajor) {
		this.anotherMajor = anotherMajor;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
	
	
	

	
}
