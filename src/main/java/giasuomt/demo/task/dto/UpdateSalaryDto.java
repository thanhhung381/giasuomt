package giasuomt.demo.task.dto;

import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import giasuomt.demo.finance.util.AmoutPerTime;
import giasuomt.demo.finance.util.UnitOfMoney;
import lombok.Getter;

@Getter
@Setter
public class UpdateSalaryDto {

	private String id;

	private Integer salaryForStudent;
	private Integer salaryForGraduatedStudent;
	private Integer salaryForTeacher;

	private AmoutPerTime salaryPerTime;

	private UnitOfMoney unitOfSalary;
}
