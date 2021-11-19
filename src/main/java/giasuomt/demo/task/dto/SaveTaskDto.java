package giasuomt.demo.task.dto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import giasuomt.demo.commondata.util.DateUtils;
import giasuomt.demo.educational.model.Subject;
import giasuomt.demo.finance.util.AmoutPerTime;
import giasuomt.demo.finance.util.PercentageOfMoney;
import giasuomt.demo.finance.util.TypeOfFee;
import giasuomt.demo.finance.util.UnitOfMoney;
import giasuomt.demo.location.model.SaveTaskPlaceAddressDto;
import giasuomt.demo.location.model.TaskPlaceAddress;
import giasuomt.demo.task.model.Require;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SaveTaskDto {

	private Long id;

	// NƠI HỌC

	private String taskPlaceType;

	private List<SaveTaskPlaceAddressDto> saveTaskPlaceAddressDtos = new ArrayList<>();// không có trước đó
	// MÔN HỌC
	// Trường nảy chỉ dùng cho API chỉnh sửa thông tin lớp, và API suggest (ko dùng
	// cho API hiển thị thông tin lớp)

	private List<Long> idSubjects = new ArrayList<>();

	// Trường này dùng cho API hiển thị thông tin lớp (để ko cần phải query thêm
	// bảng subjects)
	// Đây cũng là trường để lưu lại lịch sử nếu sau này nếu có chỉnh sửa database
	// của bảng Subject
	private String subjectApperance;

	private String subjectNote;

	// YÊU CẦU
	// Trường nảy chỉ dùng cho API chỉnh sửa thông tin lớp, và API suggest (ko dùng
	// cho API hiển thị thông tin lớp)

	private List<Long> idRequires = new ArrayList<>();

	private String requireApperance;

	private String requireNote;

	// Trường này dùng cho API hiển thị thông tin lớp (để ko cần phải query thêm
	// bảng subjects)
	// Đây cũng là trường để lưu lại lịch sử nếu sau này nếu có chỉnh sửa database
	// của bảng Subject
	// NGƯỜI ĐĂNG KÝ và HỌC VIÊN

	private List<Long> idRegisters = new ArrayList<>();

	private List<Long> idLearners = new ArrayList<>();

	private int lessonNumber; // Số buổi

	
	private AmoutPerTime lessonNumberPerTime; // Số buổi tính theo

	private float hour; // Số giờ

	
	private AmoutPerTime hourPerTime; // Số giờ tính theo

	private String freeTime;


	private LocalDateTime startDate;

	// HỌC PHÍ
	private int salary;

	private UnitOfMoney unitOfSalary;

	private AmoutPerTime salaryPerTime;

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
	
	private String taskCode;
	
	
	
}
