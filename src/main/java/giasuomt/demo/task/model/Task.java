package giasuomt.demo.task.model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;
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
import giasuomt.demo.educational.model.Subject;
import giasuomt.demo.finance.util.AmoutPerTime;
import giasuomt.demo.finance.util.PercentageOfMoney;
import giasuomt.demo.finance.util.TypeOfFee;
import giasuomt.demo.finance.util.UnitOfMoney;
import giasuomt.demo.job.model.Job;
import giasuomt.demo.location.model.TaskPlaceAddress;
import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.task.util.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "task")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class Task extends AbstractEntityNotId {
	// @Unique
	// @NotBlank
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(nullable = false)
	private String id; // Cần viết tự generae theo dạng MB1991 
	
	


//TÌNH TRẠNG LỚP
//	@Enumerated(EnumType.STRING) 
//	@NotNull  //kiểu Enum mình ko nên để @NotBlank mà nên để @NotNull
//	private TaskStatus status; 

//NƠI HỌC
	private String taskPlaceType;

	@OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TaskPlaceAddress> taskPlaceAddresses = new LinkedList<>();// không có trước đó

//MÔN HỌC
	// Trường nảy chỉ dùng cho API chỉnh sửa thông tin lớp, và API suggest (ko dùng
	// cho API hiển thị thông tin lớp)
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "task_subject", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
	private List<Subject> subjects = new LinkedList<>();

	// Trường này dùng cho API hiển thị thông tin lớp (để ko cần phải query thêm
	// bảng subjects)
	// Đây cũng là trường để lưu lại lịch sử nếu sau này nếu có chỉnh sửa database
	// của bảng Subject
	private String subjectApperance;

	private String subjectNote;

//YÊU CẦU
	// Trường nảy chỉ dùng cho API chỉnh sửa thông tin lớp, và API suggest (ko dùng
	// cho API hiển thị thông tin lớp)
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "task_require", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "require_id"))
	private List<Require> requires = new LinkedList<>();

	// Trường này dùng cho API hiển thị thông tin lớp (để ko cần phải query thêm
	// bảng subjects)
	// Đây cũng là trường để lưu lại lịch sử nếu sau này nếu có chỉnh sửa database
	// của bảng Subject
	private String requireApperance;

	private String requireNote;

//THỜI GIAN
	private int lessonNumber; // Số buổi

	@Enumerated(EnumType.STRING)
	private AmoutPerTime lessonNumberPerTime; // Số buổi tính theo

	private float hour; // Số giờ

	@Enumerated(EnumType.STRING)
	private AmoutPerTime hourPerTime; // Số giờ tính theo

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

//	private AmoutPerTime salaryPerTime;

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
	private List<TaskComment> comments=new LinkedList<>();

//ĐÁNH DẤU (Nếu có)
//	@OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
//	private Set<TaskSign> taskSigns;

//NGƯỜI ĐĂNG KÝ và HỌC VIÊN
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "task_register", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "register_id"))
	private List<RegisterAndLearner> registers = new LinkedList<>();

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "task_learner", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "learner_id"))
	private List<RegisterAndLearner> learners = new LinkedList<>();

//ỨNG VIÊN ĐĂNG KÝ
	@OneToMany(mappedBy = "task")
	private List<Application> applications=new LinkedList<>();

//GIAO JOB
	@OneToMany(mappedBy = "task")
	private List<Job> jobs=new LinkedList<>();

//số Task khởi tạo	

	
	
	
	public void removeApplication(Application application)
	{
		this.applications.remove(application);
	}
	
	public void addApplication(Application application)
	{
		application.setTask(this);
		this.applications.add(application);
	}
	
	
	
	public void addTaskPlaceAddress(TaskPlaceAddress taskPlaceAddress) {
		taskPlaceAddress.setTask(this);
		this.taskPlaceAddresses.add(taskPlaceAddress);
	}

	public void removeTaskPlaceAddress(TaskPlaceAddress taskPlaceAddress) {
		this.taskPlaceAddresses.remove(taskPlaceAddress);
	}



}