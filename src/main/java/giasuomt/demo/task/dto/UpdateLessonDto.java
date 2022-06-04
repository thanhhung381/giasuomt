package giasuomt.demo.task.dto;

import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import giasuomt.demo.finance.util.AmoutPerTime;
import lombok.Getter;

@Getter
@Setter
public class UpdateLessonDto {
	
	private String id;
	
	private String  lessonNumber; // Số buổi
	
	private String lessonPeriodOfTime;
}
