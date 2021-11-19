package giasuomt.demo.person.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import giasuomt.demo.commondata.model.Person;
import giasuomt.demo.location.model.Area;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "learner")
@Getter
@Setter
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class Learner extends Person {
	// @Column(updatable = false) //Column này ko update được
	// @Column(unique = true)
	private String learnerCode; // Cần viết tự generate theo dạng 8 số

	private String addNo;

	private String addSt;

	private String addNote;

//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "area_id")
//	private Area area;

	// MEDIA
	private String avatar;

	private String publicImgs;

	private String privateImgs;

	private String infoImgs;

//	// HIỆN ĐANG LÀ
//	@OneToMany(mappedBy = "learner", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Schooler> schoolers = new LinkedList<>();
//	
//	@OneToMany(mappedBy = "learner", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Student> students = new LinkedList<>();
//
//	@OneToMany(mappedBy = "learner", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Worker> workers = new LinkedList<>();
	
	//PERSONAL RELATIONSHIP:
//	@OneToMany(mappedBy = "personA", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Relationship> relationshipWith = new LinkedList<>();
//
//	@OneToMany(mappedBy = "personB", cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonIgnore
//	private List<Relationship> relationshipBy = new LinkedList<>();
	
	
//	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
//	@JoinTable(name = "tutor_certificate", joinColumns = @JoinColumn(name = "tutor_id"), inverseJoinColumns = @JoinColumn(name = "certificate_id"))
//	private List<Certificate> certificates = new LinkedList<>();

	private String learnerNotices;
	
	//LEARNER/REGISTER
//	private String learnerNotices;
//
//	@ManyToMany(mappedBy = "registers")
//	@JsonIgnore
//	private List<Task> registerOfTasks = new LinkedList<>();
//
//	@ManyToMany(mappedBy = "learners")
//	@JsonIgnore
//	private List<Task> learnerOfTasks = new LinkedList<>();
}
