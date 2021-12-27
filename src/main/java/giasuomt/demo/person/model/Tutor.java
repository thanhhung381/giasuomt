package giasuomt.demo.person.model;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import giasuomt.demo.commondata.model.Person;
import giasuomt.demo.educational.model.Subject;
import giasuomt.demo.job.model.Job;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.tags.model.TutorTag;
import giasuomt.demo.task.model.Application;
import giasuomt.demo.task.model.Task;
import giasuomt.demo.user.model.User;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tutor")
@Getter
@Setter
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
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
	private List<Student> students = new LinkedList<>();

	@OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<GraduatedStudent> graduatedStudents = new LinkedList<>();

	@OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<InstitutionTeacher> institutionTeachers = new LinkedList<>();

	@OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SchoolTeacher> schoolTeachers = new LinkedList<>();

	@OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Worker> workers = new LinkedList<>();

//PERSONAL RELATIONSHIP:
//	@OneToMany(mappedBy = "personA", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Relationship> relationshipWith = new LinkedList<>();
//
//	@OneToMany(mappedBy = "personB", cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonIgnore
//	private List<Relationship> relationshipBy = new LinkedList<>();

//TUTOR:
	private String voices;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "tutor_tutor_tag", joinColumns = @JoinColumn(name = "tutor_id"), inverseJoinColumns = @JoinColumn(name = "tutor_tag_id"))
	private List<TutorTag> tutorTags = new LinkedList<>();

	private String tutorNotices;

	private String advantageNote;

	@OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Application> applications = new LinkedList<>();

	@OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Job> jobs = new LinkedList<>();

	private Double exp;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "tutor_Subject", joinColumns = @JoinColumn(name = "tutor_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
	private List<Subject> registeredSubjects = new LinkedList<>();
	
	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	//Subject Group
	private String subjectGroupMaybe;
	
	private String subjectGroupSure;

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
