package giasuomt.demo.person.model;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import giasuomt.demo.commondata.model.Person;
import giasuomt.demo.location.model.RegisterAndLearnerAddress;
import giasuomt.demo.tags.model.RegisterAndLearnerTag;
import giasuomt.demo.task.model.Task;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "register-and-learner")
@Getter
@Setter
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class RegisterAndLearner extends Person {
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
	@JoinTable(name = "register-and-learner_register-and-learner-tag", joinColumns = @JoinColumn(name = "register-and-learner_id"), inverseJoinColumns = @JoinColumn(name = "register-and-learner-tag_id"))
	private List<RegisterAndLearnerTag> registerAndLearnerTags = new LinkedList<>();
	
	//RegisterAndLearner RELATIONSHIP:
	@OneToMany(mappedBy = "personA", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Relationship> relationshipWith = new LinkedList<>();

	@OneToMany(mappedBy = "personB", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Relationship> relationshipBy = new LinkedList<>();
	
	
	private String registerAndLearnerAddressNotices;
	
	
	//LEARNER/REGISTER
	@ManyToMany(mappedBy = "registers")
	@JsonIgnore
	private List<Task> registerOfTasks = new LinkedList<>();

	@ManyToMany(mappedBy = "learners")
	@JsonIgnore
	private List<Task> learnerOfTasks = new LinkedList<>();
}
