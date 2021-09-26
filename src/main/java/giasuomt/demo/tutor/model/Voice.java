package giasuomt.demo.tutor.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import giasuomt.demo.commondata.model.AbstractEntity;

@Entity
@Table(name = "voice")
public class Voice extends AbstractEntity {
	@Id //Quy định khoá chính
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Sinh ra các giá trị là độc lập cho các model khác nhau
	@Column(updatable = false) //Column này ko update được
	private Long id; //(khai báo là protected để các lớp con có thể truy cập vô để kế thừa)
	
	private String voiceName;
	
	private String description;
	
	@ManyToMany(mappedBy = "voices", fetch = FetchType.LAZY)
	@JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
	private Set<Tutor> tutor = new HashSet<>();
	
	
	//getter,setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVoiceName() {
		return voiceName;
	}

	public void setVoiceName(String voiceName) {
		this.voiceName = voiceName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Tutor> getTutor() {
		return tutor;
	}

	public void setTutor(Set<Tutor> tutor) {
		this.tutor = tutor;
	}
	
	
	
}
