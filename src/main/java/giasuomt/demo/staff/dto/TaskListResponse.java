package giasuomt.demo.staff.dto;
import java.time.LocalDateTime;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import giasuomt.demo.finance.util.AmoutPerTime;
import giasuomt.demo.finance.util.PercentageOfMoney;
import giasuomt.demo.finance.util.TypeOfFee;
import giasuomt.demo.finance.util.UnitOfMoney;
import giasuomt.demo.job.model.Job;
import giasuomt.demo.location.model.TaskPlaceAddress;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskListResponse {

	protected Long id; 
	
	
	protected Long version; 
	
	// @Unique
	// @NotBlank
	private String taskCode; // Cần viết tự generate theo dạng MB1991

	// TÌNH TRẠNG LỚP
//		@Enumerated(EnumType.STRING) 
//		@NotNull  //kiểu Enum mình ko nên để @NotBlank mà nên để @NotNull
//		private TaskStatus status;

	// NƠI HỌC
	private String taskPlaceType;

	private Set<TaskPlaceAddress> taskPlaceAddresses = new HashSet<>();// không có trước đó

	// MÔN HỌC
	// Trường nảy chỉ dùng cho API chỉnh sửa thông tin lớp, và API suggest (ko dùng
	// cho API hiển thị thông tin lớp)

	//private List<Subject> subjects = new ArrayList<>();

	// Trường này dùng cho API hiển thị thông tin lớp (để ko cần phải query thêm
	// bảng subjects)
	// Đây cũng là trường để lưu lại lịch sử nếu sau này nếu có chỉnh sửa database
	// của bảng Subject
	private String subjectApperance;

	private String subjectNote;

	// YÊU CẦU
	// Trường nảy chỉ dùng cho API chỉnh sửa thông tin lớp, và API suggest (ko dùng
	// cho API hiển thị thông tin lớp)



	// Trường này dùng cho API hiển thị thông tin lớp (để ko cần phải query thêm
	// bảng subjects)
	// Đây cũng là trường để lưu lại lịch sử nếu sau này nếu có chỉnh sửa database
	// của bảng Subject
	private String requireApperance;

	private String requireNote;

	// THỜI GIAN
	private int lessonNumber; // Số buổi

	private AmoutPerTime lessonNumberPerTime; // Số buổi tính theo

	private float hour; // Số giờ

	private AmoutPerTime hourPerTime; // Số giờ tính theo

	private String freeTime;

	private LocalDateTime startDate;

	// HỌC PHÍ
	private int salary;

	private UnitOfMoney unitOfSalary;

//		private AmoutPerTime salaryPerTime;

	// PHÍ THU

	private TypeOfFee typeOfTaskFee;

	private int taskFee;

	private UnitOfMoney unitOfTaskFee;

	private PercentageOfMoney percentageOfTaskFeeInSalary;

	// PHÍ LIÊN KẾT (NẾU CÓ)

	private TypeOfFee typeOfAffiliateFee;

	private int affiliateFee;

	private UnitOfMoney unitOfAffiliateFee;

	private PercentageOfMoney percentageOfAffiliateFeeInTaskFee;
	// COMMENTS (Nếu có)
//	    @OneToMany(mappedBy = "task")
	// private Set<Comment> comments;

	// ĐÁNH DẤU (Nếu có)
//		@OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
//		private Set<TaskSign> taskSigns;

	// NGƯỜI ĐĂNG KÝ và HỌC VIÊN

//	private List<Person> registers = new ArrayList<>();
//
//	private List<Person> learners = new ArrayList<>();

	private Set<ApplicationResponse> applications = new HashSet<>();

	private Set<Job> jobs = new HashSet<>();

}
