package giasuomt.demo.tutor.model;

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

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.institution.model.Institution;
import giasuomt.demo.institution.model.Major;

@Entity
@Table(name = "graduated_student")
public class GraduatedStudent extends AbstractEntity {
	@Id //Quy định khoá chính
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Sinh ra các giá trị là độc lập cho các model khác nhau
	@Column(updatable = false) //Column này ko update được
	private Long id; //(khai báo là protected để các lớp con có thể truy cập vô để kế thừa)
	
	@ElementCollection(targetClass=String.class)
	private Set<String> diplomaImg;
	
	private String graduatedYear;
	
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

	public void setId(Long id) {
		this.id = id;
	}

	public Set<String> getDiplomaImg() {
		return diplomaImg;
	}

	public void setDiplomaImg(Set<String> diplomaImg) {
		this.diplomaImg = diplomaImg;
	}

	public String getGraduatedYear() {
		return graduatedYear;
	}

	public void setGraduatedYear(String graduatedYear) {
		this.graduatedYear = graduatedYear;
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
