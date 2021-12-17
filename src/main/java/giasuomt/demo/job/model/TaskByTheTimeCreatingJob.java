package giasuomt.demo.job.model;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.commondata.model.AbstractEntityNotId;
import giasuomt.demo.commondata.util.DateUtils;
import giasuomt.demo.educational.model.Subject;
import giasuomt.demo.finance.util.AmoutPerTime;
import giasuomt.demo.finance.util.PercentageOfMoney;
import giasuomt.demo.finance.util.TypeOfFee;
import giasuomt.demo.finance.util.UnitOfMoney;
import giasuomt.demo.location.model.TaskPlaceAddress;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.task.model.Application;
import giasuomt.demo.task.model.Require;
import giasuomt.demo.task.model.Task;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Task_By_The_Time_Creating_Job")
@Getter
@Setter
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class TaskByTheTimeCreatingJob extends AbstractEntityNotId {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long keyId;

	// @GeneratedValue(strategy = GenerationType.SEQUENCE)
	// @Column(nullable = false)
	private String id; // Cần viết tự generae theo dạng MB1991

//TÌNH TRẠNG LỚP
//	@Enumerated(EnumType.STRING) 
//	@NotNull  //kiểu Enum mình ko nên để @NotBlank mà nên để @NotNull
//	private TaskStatus status; 

//NƠI HỌC
	private String taskPlaceType;

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

	@ElementCollection
	private List<String> taskPlaceAddresses = new LinkedList<>();

}
