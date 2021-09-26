package giasuomt.demo.tutor.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.institution.model.Institution;

@Entity
@Table(name = "school_teacher")
public class SchoolTeacher extends AbstractEntity {
	@Id //Quy định khoá chính
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Sinh ra các giá trị là độc lập cho các model khác nhau
	@Column(updatable = false) //Column này ko update được
	private Long id; //(khai báo là protected để các lớp con có thể truy cập vô để kế thừa)
	
	@ElementCollection(targetClass=String.class)
	private Set<String> confirmImg;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name = "school_teacher_institution",
	                   joinColumns = @JoinColumn(name = "school_teacher_id"),
	                   inverseJoinColumns = @JoinColumn(name = "institution_id"))
	private Set<Institution> institutions = new HashSet<>();
	
	private String subject;
	
	
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

	public Set<String> getConfirmImg() {
		return confirmImg;
	}

	public void setConfirmImg(Set<String> confirmImg) {
		this.confirmImg = confirmImg;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
	
	
}
