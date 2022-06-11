package giasuomt.demo.task.dto;

import java.util.HashSet;
import java.util.LinkedList;


import java.util.List;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import giasuomt.demo.commondata.util.Gender;
import giasuomt.demo.commondata.util.HienDangLa;
import giasuomt.demo.commondata.util.Voice;
import giasuomt.demo.educational.model.SubjectGroup;
import giasuomt.demo.finance.util.AmoutPerTime;
import giasuomt.demo.finance.util.UnitOfMoney;
import giasuomt.demo.location.model.TaskPlaceAddress;

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
	
	private String  lessonNumber; // Số buổi
	
	private String lessonPeriodOfTime;

	
	private String freeTime;
	
	private int salary;
	
	private UnitOfMoney unitOfSalary;
	
	private AmoutPerTime salaryPerTime;
	

	
	private String taskPlaceType;
	

	private Set<Gender> genderRequired=new HashSet<>();
	
	private Set<SubjectGroup> subjectGroups=new HashSet<>();
	
	private Set<HienDangLa> hienDangLaRequired=new HashSet<>();
	
	private Set<Voice> voiceRequired=new HashSet<>();
	
	// Số học viên
		private String learnerNumber;
	//Ghi Chú cho gia sư
	
}
