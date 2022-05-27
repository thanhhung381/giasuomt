package giasuomt.demo.person.model;

import java.time.LocalDateTime;
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

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import giasuomt.demo.commondata.model.Person;
import giasuomt.demo.commondata.util.Calendar;
import giasuomt.demo.commondata.util.DateTimeUtils;
import giasuomt.demo.commondata.util.HienDangLa;
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
	
		@NamedAttributeNode("calendars"),
		@NamedAttributeNode("hienDangLas"),
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

	
	@ElementCollection
	private Set<String> publicImgs=new HashSet<>();

	
	@ElementCollection
	private Set<String> privateImgs=new HashSet<>();


//HIỆN ĐANG LÀ
	
	@ElementCollection(targetClass = HienDangLa.class)
	@Enumerated(EnumType.STRING)
	private Set<HienDangLa>  hienDangLas=new HashSet<>();
	
	private int nowLevel;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATE_TIME_FORMAT) //Quy định date format khi nó add đối tượng thành Json để trả về Clients
	@DateTimeFormat(pattern = DateTimeUtils.DATE_TIME_FORMAT) 
	private LocalDateTime nowLevelUpdatedAt;
	
	private String studyingInsitution;
	
	private String teachingInstitution;
	
	private String major;



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
	private Set<TutorReview> tutorReviews=new HashSet<>();
	
	private Double averageStarNumbers;
	
	@ElementCollection(targetClass = Calendar.class)
	@Enumerated(EnumType.STRING)
	private Set<Calendar> calendars=new HashSet<>(); 
	

// FOR API SAVE



}
