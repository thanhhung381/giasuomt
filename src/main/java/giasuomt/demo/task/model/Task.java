package giasuomt.demo.task.model;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import giasuomt.demo.comment.model.Comment;
import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.commondata.util.DateUtils;
import giasuomt.demo.educational.model.Subject;
import giasuomt.demo.finance.util.AmoutPerTime;
import giasuomt.demo.finance.util.PercentageOfMoney;
import giasuomt.demo.finance.util.TypeOfFee;
import giasuomt.demo.finance.util.UnitOfMoney;
import giasuomt.demo.job.model.Job;
import giasuomt.demo.person.model.Person;
import giasuomt.demo.task.util.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "task")
public class Task extends AbstractEntity {
	//@Unique
	@NotBlank
	private String taskCode; //Cần viết tự generate theo dạng MB1991
		
//TÌNH TRẠNG LỚP
	@Enumerated(EnumType.STRING) 
	@NotNull  //kiểu Enum mình ko nên để @NotBlank mà nên để @NotNull
	private TaskStatus status;
	
//NƠI HỌC
	private String taskPlaceType; 
	
	@OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
	private Set<TaskPlaceAddress> taskPlaceAddresses;
	
//MÔN HỌC
	//Trường nảy chỉ dùng cho API chỉnh sửa thông tin lớp, và API suggest (ko dùng cho API hiển thị thông tin lớp)
//	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//	@JoinTable(name = "task_subject", 
//	                   joinColumns = @JoinColumn(name = "task_id"),
//	                   inverseJoinColumns = @JoinColumn(name = "subject_id"))
//	private Set<Subject> subjects = new HashSet<>();
	
	//Trường này dùng cho API hiển thị thông tin lớp (để ko cần phải query thêm bảng subjects)
	//Đây cũng là trường để lưu lại lịch sử nếu sau này nếu có chỉnh sửa database của bảng Subject
	private String subjectApperance; 
	
	private String subjectNote;

//YÊU CẦU
	//Trường nảy chỉ dùng cho API chỉnh sửa thông tin lớp, và API suggest (ko dùng cho API hiển thị thông tin lớp)
//	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//	@JoinTable(name = "task_require",
//	                   joinColumns = @JoinColumn(name = "task_id"),
//	                   inverseJoinColumns = @JoinColumn(name = "require_id"))
//	private Set<Require> requires = new HashSet<>();
	
	//Trường này dùng cho API hiển thị thông tin lớp (để ko cần phải query thêm bảng subjects)
	//Đây cũng là trường để lưu lại lịch sử nếu sau này nếu có chỉnh sửa database của bảng Subject
	private String requireApperance; 
	
	private String requireNote;	
	  
//THỜI GIAN
	private int lessonNumber; //Số buổi
	
	private AmoutPerTime lessonNumberPerTime; //Số buổi tính theo
	
	private float hour; //Số giờ
	
	private AmoutPerTime hourPerTime; //Số giờ tính theo
	
	private String freeTime;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT) //Quy định date format khi nó add đối tượng thành Json để trả về Clients
	@DateTimeFormat(pattern = DateUtils.DATE_FORMAT) //Quy định date format để lưu xuống database
	private LocalDateTime startDate; 

//HỌC PHÍ
	private int salary; 
	
	private UnitOfMoney unitOfSalary;
	
	private AmoutPerTime salaryPerTime;
	
//PHÍ THU
	private TypeOfFee typeOfTaskFee;
	
	private int taskFee;
	
	private UnitOfMoney unitOfTaskFee;
	
	private PercentageOfMoney percentageOfTaskFeeInSalary;
	
//PHÍ LIÊN KẾT (NẾU CÓ)
	private TypeOfFee typeOfAffiliateFee;
	
	private int affiliateFee;
	
	private UnitOfMoney unitOfAffiliateFee;
	
	private PercentageOfMoney percentageOfAffiliateFeeInTaskFee;	

//COMMENTS (Nếu có)
    @OneToMany(mappedBy = "task")
    private Set<Comment> comments;

//ĐÁNH DẤU (Nếu có)
	@OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
	private Set<TaskSign> taskSigns;

	
	

//NGƯỜI ĐĂNG KÝ và HỌC VIÊN
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name = "task_register",
		       joinColumns = @JoinColumn(name = "task_id"),
		       inverseJoinColumns = @JoinColumn(name = "register_id"))
	private Set<Person> registers = new HashSet<>();

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name = "task_learner",
		       joinColumns = @JoinColumn(name = "task_id"),
		       inverseJoinColumns = @JoinColumn(name = "learner_id"))
	private Set<Person> learners = new HashSet<>();

//ỨNG VIÊN ĐĂNG KÝ
	@OneToMany(mappedBy = "task")
	private Set<Application> applications;

//GIAO JOB
	@OneToMany(mappedBy = "task")
	private Set<Job> jobs;

}