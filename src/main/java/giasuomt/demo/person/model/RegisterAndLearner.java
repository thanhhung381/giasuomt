package giasuomt.demo.person.model;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import giasuomt.demo.commondata.model.Person;
import giasuomt.demo.location.model.RegisterAndLearnerAddress;
import giasuomt.demo.location.model.TaskPlaceAddress;
import giasuomt.demo.tags.model.RegisterAndLearnerTag;
import giasuomt.demo.task.model.Application;
import giasuomt.demo.task.model.Registration;
import giasuomt.demo.task.model.Task;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "register_and_learner")
@Getter
@Setter
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class RegisterAndLearner extends Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String vocative;
	
	@OneToMany(mappedBy = "registerAndLearner", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RegisterAndLearnerAddress> registerAndLearnerAddresses = new LinkedList<>();// không có trước đó

	// MEDIA
	private String avatar;

	private String publicImgs;

	private String privateImgs;

	private String infoImgs;
	
	// HIỆN ĐANG LÀ
	@OneToMany(mappedBy = "registerAndLearner", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Schooler> schoolers = new LinkedList<>();
	
	@OneToMany(mappedBy = "registerAndLearner", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Student> students = new LinkedList<>();

	@OneToMany(mappedBy = "registerAndLearner", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Worker> workers = new LinkedList<>();
	
	//TAGS
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "register_and_learner_register_and_learner_tag", joinColumns = @JoinColumn(name = "register_and_learner_id"), inverseJoinColumns = @JoinColumn(name = "register_and_learner_tag_id"))
	private List<RegisterAndLearnerTag> registerAndLearnerTags = new LinkedList<>();
	
	//RegisterAndLearner RELATIONSHIP:
	@OneToMany(mappedBy = "personA", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RegisterAndLearnerRelationship> relationshipWith = new LinkedList<>();

	@OneToMany(mappedBy = "personB", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<RegisterAndLearnerRelationship> relationshipBy = new LinkedList<>();  
	

	private String note;
	
	
//LEARNER/REGISTER	
	@OneToMany(mappedBy = "registerAndLearner", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Registration> registrations = new LinkedList<>();	
	
	
	
	public void addRegisterAndLearnerAddress(RegisterAndLearnerAddress registerAndLearnerAddress) {
		registerAndLearnerAddress.setRegisterAndLearner(this);
		this.registerAndLearnerAddresses.add(registerAndLearnerAddress);
	}

	public void removeRegisterAndLearnerAddress(RegisterAndLearnerAddress registerAndLearnerAddress) {
		this.registerAndLearnerAddresses.remove(registerAndLearnerAddress);
	}

	public void addSchooler(Schooler schooler) {
		schooler.setRegisterAndLearner(this);
		this.schoolers.add(schooler);
	}

	public void removeSchooler(Schooler schooler) {
		this.schoolers.remove(schooler);
	}
	
	public void addStudent(Student student) {
		student.setRegisterAndLearner(this);
		this.students.add(student);
	}

	public void removeStudent(Student student) {
		this.students.remove(student);
	}
	
	public void addWorker(Worker worker) {
		worker.setRegisterAndLearner(this);
		this.workers.add(worker);
	}

	public void removeWorker(Worker worker) {
		this.workers.remove(worker);
	}
	
	
	public void removeRelationshipWith(RegisterAndLearnerRelationship relationship) {
		this.relationshipWith.remove(relationship);
	}

	public void addRelationshipWith(RegisterAndLearnerRelationship relationship) {
		relationship.setPersonA(this);
		this.relationshipWith.add(relationship);
	}
}
