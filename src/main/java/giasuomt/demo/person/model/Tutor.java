package giasuomt.demo.person.model;

import java.util.HashSet;
import java.util.LinkedList;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import giasuomt.demo.commondata.model.Person;
import giasuomt.demo.commondata.util.Voice;
import giasuomt.demo.educational.model.SubjectGroup;
import giasuomt.demo.job.model.Job;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.tags.model.TutorTag;
import giasuomt.demo.task.model.Application;
import giasuomt.demo.task.model.Task;
import giasuomt.demo.tutorReview.model.TutorReview;
import giasuomt.demo.user.model.User;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Tutor")
@Getter
@Setter
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
@NamedEntityGraph(name = "tutor",attributeNodes = {
		@NamedAttributeNode("workers"),
		@NamedAttributeNode("students"),
		@NamedAttributeNode("schoolTeachers"),
		@NamedAttributeNode("institutionTeachers"),
		@NamedAttributeNode("graduatedStudents"),
		@NamedAttributeNode("tutorTags"),
		@NamedAttributeNode("tempArea"),
		@NamedAttributeNode("relArea"),
		@NamedAttributeNode("perArea"),
		@NamedAttributeNode("subjectGroupMaybes"),
		@NamedAttributeNode("subjectGroupSures"),
		@NamedAttributeNode("voices")
		
})
public class Tutor extends Person { 
	// @Column(updatable = false) //Column này ko update được
	// @Column(unique = true)
	@Id
	private Long id; // Cần viết tự generate theo dạng 8 số

	private String tempAddNo;

	private String tempAddSt;

	private String tempAddNote;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "temp_area_id")
	private Area tempArea;

	private String perAddNo;

	private String perAddSt;

	private String perAddNote;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "per_area_id")
	private Area perArea;

//VỊ TRÍ TƯƠNG ĐỐI CỦA GIA SƯ - vị trí này được xác định theo: vị trí các lớp đã nhận (trọng số theo thời gian và số lớp), các lớp đã đăng ký (trọng số theo thời gian và số lớp), nơi ở hiện tại mà gia sư khai báo (trọng số theo thời gian và nơi ở là tạm trú hay thường trú) 
	private String xRelCoo;

	private String yRelCoo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rel_area_id")
	private Area relArea;

//MEDIA	
	private String avatar;

	private String publicImgs;

	private String privateImgs;

	private String infoImgs;

//HIỆN ĐANG LÀ
	@OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Student> students = new HashSet<>();

	@OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<GraduatedStudent> graduatedStudents = new HashSet<>();

	@OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<InstitutionTeacher> institutionTeachers =  new HashSet<>();

	@OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<SchoolTeacher> schoolTeachers = new HashSet<>();

	@OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Worker> workers =  new HashSet<>();

//PERSONAL RELATIONSHIP:
//	@OneToMany(mappedBy = "personA", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Relationship> relationshipWith = new LinkedList<>();
//
//	@OneToMany(mappedBy = "personB", cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonIgnore
//	private List<Relationship> relationshipBy = new LinkedList<>();

//TUTOR:
	@ElementCollection(targetClass = Voice.class)
	@Enumerated(EnumType.STRING)
	private Set<Voice> voices=new HashSet<>();

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "tutor_tutor_tag", joinColumns = @JoinColumn(name = "tutor_id"), inverseJoinColumns = @JoinColumn(name = "tutor_tag_id"))
	private Set<TutorTag> tutorTags = new HashSet<>();

	private String tutorNotices;

	private String advantageNote;

	@OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Application> applications = new LinkedList<>();

	@OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Job> jobs = new HashSet<>();

	private Double exp;

	
	@OneToOne(mappedBy = "tutor")
	@JsonIgnore
	private User user;
	
	//Subject Group
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "task_subjectGroupMaybe", joinColumns = @JoinColumn(name = "tutor_id"), inverseJoinColumns = @JoinColumn(name = "subjectGroup_id"))
	private Set<SubjectGroup> subjectGroupMaybes=new HashSet<>();

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "task_subjectGroupSure", joinColumns = @JoinColumn(name = "tutor_id"), inverseJoinColumns = @JoinColumn(name = "subjectGroup_id"))
	private Set<SubjectGroup> subjectGroupSures=new HashSet<>();

	
	//tutor
	@OneToMany(mappedBy = "tutor")
	@JsonIgnore
	private List<TutorReview> tutorReviews=new LinkedList<>();
	
	private Double averageStarNumbers;
	

// FOR API SAVE
	public void addWorker(Worker worker) {
		worker.setTutor(this);
		this.workers.add(worker);
	}

	public void removeWorker(Worker worker) {
		this.workers.remove(worker);
	}

	public void addStudent(Student student) {
		student.setTutor(this);
		this.students.add(student);
	}

	public void removeStudent(Student student) {
		this.students.remove(student);
	}

	public void addGraduatedStudent(GraduatedStudent graduatedStudent) {
		graduatedStudent.setTutor(this);
		this.graduatedStudents.add(graduatedStudent);
	}

	public void removeGraduatedStudent(GraduatedStudent graduatedStudent) {
		this.graduatedStudents.remove(graduatedStudent);
	}

	public void addInstitutionTeacher(InstitutionTeacher institutionTeacher) {
		institutionTeacher.setTutor(this);
		this.institutionTeachers.add(institutionTeacher);
	}

	public void removeInstitutionTeacher(InstitutionTeacher institutionTeacher) {
		this.institutionTeachers.remove(institutionTeacher);
	}

	public void addSchoolTeacher(SchoolTeacher schoolTeacher) {
		schoolTeacher.setTutor(this);
		this.schoolTeachers.add(schoolTeacher);
	}

	public void removeSchoolTeacher(SchoolTeacher schoolTeacher) {
		this.schoolTeachers.remove(schoolTeacher);
	}

}
