package giasuomt.demo.task.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import giasuomt.demo.finance.util.AmoutPerTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UpdateHourDto {
	
	
	private String id;

	private AmoutPerTime lessonNumberPerTime; // Số buổi tính theo

	private float hour; // Số giờ

	
	private AmoutPerTime hourPerTime; // Số giờ tính theo
	
}
