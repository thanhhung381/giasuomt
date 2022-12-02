package giasuomt.demo.task.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import giasuomt.demo.finance.util.AmoutPerTime;
import giasuomt.demo.finance.util.PercentageOfMoney;
import giasuomt.demo.finance.util.TypeOfFee;
import giasuomt.demo.finance.util.UnitOfMoney;
import giasuomt.demo.location.dto.SaveTaskPlaceAddressDto;
import giasuomt.demo.task.util.TaskSign;
import giasuomt.demo.task.util.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SaveTaskDto {

	private String id;

	// NƠI HỌC

	private String taskPlaceType;

	private Set<SaveTaskPlaceAddressDto> saveTaskPlaceAddressDtos = new HashSet<>();// không có trước đó

	private TaskStatus status;

	// MÔN HỌC
	// Trường nảy chỉ dùng cho API chỉnh sửa thông tin lớp, và API suggest (ko dùng
	// cho API hiển thị thông tin lớp)

	// Trường này dùng cho API hiển thị thông tin lớp (để ko cần phải query thêm
	// bảng subjects)
	// Đây cũng là trường để lưu lại lịch sử nếu sau này nếu có chỉnh sửa database
	// của bảng Subject

	private String subjectNote;

	// YÊU CẦU
	// Trường nảy chỉ dùng cho API chỉnh sửa thông tin lớp, và API suggest (ko dùng
	// cho API hiển thị thông tin lớp)

	private Set<String> idSubjectGroup = new HashSet<>();

	private String requireNote;

	private String lessonNumber; // Số buổi

	private String lessonPeriodOfTime;

	private String freeTime;

	private LocalDateTime startDate;

	// HỌC PHÍ
	private Integer salaryForStudent;
	private Integer salaryForGraduatedStudent;
	private Integer salaryForTeacher;

	private UnitOfMoney unitOfSalary;

	private AmoutPerTime salaryPerTime;

	// PHÍ THU
	private TypeOfFee typeOfTaskFee;

	private Integer taskFeeForStudent;
	private Integer taskFeeForGraduatedStudent;
	private Integer taskFeeForTeacher;

	private UnitOfMoney unitOfTaskFee;

	private PercentageOfMoney percentageOfTaskFeeInSalary;

	// PHÍ LIÊN KẾT (NẾU CÓ)
	private TypeOfFee typeOfAffiliateFee;

	private Integer affiliateFee;

	private UnitOfMoney unitOfAffiliateFee;

	private PercentageOfMoney percentageOfAffiliateFeeInTaskFee;

	// Số học viên
	private String learnerNumber;

	private String learnerNote;


	private Set<String> genderRequired = new HashSet<>();
	
	Set<TaskSign> taskSigns = new HashSet<>();

	private String voiceRequired;

	private String hienDangLaRequired;

	private Integer nowLevelRequired;

}
