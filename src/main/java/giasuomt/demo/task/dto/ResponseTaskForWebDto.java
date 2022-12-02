package giasuomt.demo.task.dto;

import java.util.HashSet;
import java.util.Set;

import giasuomt.demo.educational.model.SubjectGroup;
import giasuomt.demo.finance.util.AmoutPerTime;
import giasuomt.demo.finance.util.UnitOfMoney;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseTaskForWebDto {

	private String id;

	private String learnerNote;

	private String requireNote;

	private String subjectNote;

	private Set<ResponseTaskPlaceAddressDto> taskPlaceAddresses = new HashSet<>();

	private String lessonNumber; // Số buổi

	private String lessonPeriodOfTime;

	private String freeTime;

	private Integer salaryForStudent;
	private Integer salaryForGraduatedStudent;
	private Integer salaryForTeacher;

	private UnitOfMoney unitOfSalary;

	private AmoutPerTime salaryPerTime;

	private String taskPlaceType;

	private Set<String> genderRequired = new HashSet<>();

	private Set<SubjectGroup> subjectGroups = new HashSet<>();

	private String hienDangLaRequired ;

	private String voiceRequired ;

	// Số học viên
	private String learnerNumber;
	// Ghi Chú cho gia sư

}
