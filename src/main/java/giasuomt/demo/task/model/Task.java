package giasuomt.demo.task.model;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import giasuomt.demo.comment.model.Comment;
import giasuomt.demo.comment.model.TaskComment;
import giasuomt.demo.commondata.generator.TaskCodeGenerator;
import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.commondata.model.AbstractEntityNotId;
import giasuomt.demo.commondata.util.DateUtils;
import giasuomt.demo.commondata.util.Gender;
import giasuomt.demo.commondata.util.HienDangLa;
import giasuomt.demo.commondata.util.Voice;
import giasuomt.demo.educational.model.SubjectGroup;
import giasuomt.demo.finance.util.AmoutPerTime;
import giasuomt.demo.finance.util.PercentageOfMoney;
import giasuomt.demo.finance.util.TypeOfFee;
import giasuomt.demo.finance.util.UnitOfMoney;
import giasuomt.demo.job.model.Job;
import giasuomt.demo.location.model.TaskPlaceAddress;
import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.task.util.TaskSign;
import giasuomt.demo.task.util.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "task")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
@NamedEntityGraphs({
	@NamedEntityGraph(name = "task" ,attributeNodes = {
			@NamedAttributeNode("taskPlaceAddresses"),
			@NamedAttributeNode("subjectGroups"),
			@NamedAttributeNode("taskSign"),
			@NamedAttributeNode("registrations"),
			@NamedAttributeNode("jobs"),
			@NamedAttributeNode("genderRequired"),
			@NamedAttributeNode("voiceRequired"),
			@NamedAttributeNode("hienDangLaRequired"),
			@NamedAttributeNode("taskSign"),
			@NamedAttributeNode("comments")
			
	})
})
public class Task extends AbstractEntityNotId {
	// @Unique
	// @NotBlank
	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	//@Column(nullable = false)
	private String id; // Cần viết tự generae theo dạng MB1991 
	
	


//TÌNH TRẠNG LỚP
	@Enumerated(EnumType.STRING)   //kiểu Enum mình ko nên để @NotBlank mà nên để @NotNull
	private TaskStatus status; 

//NƠI HỌC
	private String taskPlaceType;

	@OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<TaskPlaceAddress> taskPlaceAddresses = new HashSet<>();// không có trước đó

//MÔN HỌC
	// Trường nảy chỉ dùng cho API chỉnh sửa thông tin lớp, và API suggest (ko dùng
	// cho API hiển thị thông tin lớp)

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "task_subjectGroup", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "subjectGroup_id"))
	private Set<SubjectGroup> subjectGroups=new HashSet<>();
	
	// Trường này dùng cho API hiển thị thông tin lớp (để ko cần phải query thêm
	// bảng subjects)
	// Đây cũng là trường để lưu lại lịch sử nếu sau này nếu có chỉnh sửa database
	// của bảng Subject
	private String subjectApperance;

	private String subjectNote;

//YÊU CẦU
	// Trường nảy chỉ dùng cho API chỉnh sửa thông tin lớp, và API suggest (ko dùng
	// cho API hiển thị thông tin lớp)


	// Trường này dùng cho API hiển thị thông tin lớp (để ko cần phải query thêm
	// bảng subjects)
	// Đây cũng là trường để lưu lại lịch sử nếu sau này nếu có chỉnh sửa database
	// của bảng Subject
	private String requireApperance;

	private String requireNote;

//THỜI GIAN
	private String  lessonNumber; // Số buổi
	
	private String lessonPeriodOfTime;



	private String freeTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT) // Quy định date format khi nó add đối
																					// tượng thành Json để trả về
																					// Clients
	@DateTimeFormat(pattern = DateUtils.DATE_FORMAT) // Quy định date format để lưu xuống database
	private LocalDateTime startDate;

//HỌC PHÍ
	private int salary;

	@Enumerated(EnumType.STRING)
	private UnitOfMoney unitOfSalary;

	private AmoutPerTime salaryPerTime;

//PHÍ THU
	@Enumerated(EnumType.STRING)
	private TypeOfFee typeOfTaskFee;

	private int taskFee;

	@Enumerated(EnumType.STRING)
	private UnitOfMoney unitOfTaskFee;

	@Enumerated(EnumType.STRING)
	private PercentageOfMoney percentageOfTaskFeeInSalary;

//PHÍ LIÊN KẾT (NẾU CÓ)
	@Enumerated(EnumType.STRING)
	private TypeOfFee typeOfAffiliateFee;

	private int affiliateFee;

	@Enumerated(EnumType.STRING)
	private UnitOfMoney unitOfAffiliateFee;

	@Enumerated(EnumType.STRING)
	private PercentageOfMoney percentageOfAffiliateFeeInTaskFee;
//COMMENTS (Nếu có)
    @OneToMany(mappedBy = "task")
	private Set<TaskComment> comments=new HashSet<>();

//ĐÁNH DẤU (Nếu có)
//
    @ElementCollection(targetClass  = TaskSign.class)
    @Enumerated(EnumType.STRING)
    private Set<TaskSign> taskSign =new HashSet<>();

//NGƯỜI ĐĂNG KÝ/HỌC VIÊN	
	@OneToMany(mappedBy = "task")
	private Set<Registration> registrations = new HashSet<>();	

//ỨNG VIÊN ĐĂNG KÝ
	@OneToMany(mappedBy = "task")
	private Set<Application> applications = new HashSet<>();	

//GIAO JOB
	@OneToMany(mappedBy = "task")
	private Set<Job> jobs=new HashSet<>();
// Số học viên
	private String learnerNumber;
//Ghi Chú cho gia sư
	private String taskNote;

	@ElementCollection(targetClass  = Gender.class)
    @Enumerated(EnumType.STRING)
	private Set<Gender> genderRequired=new HashSet<>();
	
	@ElementCollection(targetClass = Voice.class)
	@Enumerated(EnumType.STRING)
	private Set<Voice> voiceRequired=new HashSet<>();
	
	@ElementCollection(targetClass = HienDangLa.class)
	@Enumerated(EnumType.STRING)
	private Set<HienDangLa> hienDangLaRequired=new HashSet<>();
	
	
	public void removeApplication(Application application){
		this.applications.remove(application);
	};
	
	public void addApplication(Application application){
		application.setTask(this);
		this.applications.add(application);
	};
	
	
	
	public void addTaskPlaceAddress(TaskPlaceAddress taskPlaceAddress) {
		taskPlaceAddress.setTask(this);
		this.taskPlaceAddresses.add(taskPlaceAddress);
	};

	public void removeTaskPlaceAddress(TaskPlaceAddress taskPlaceAddress) {
		this.taskPlaceAddresses.remove(taskPlaceAddress);
	};




}