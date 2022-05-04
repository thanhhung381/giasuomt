package giasuomt.demo.job.dto;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveJobDto {
	
	private Long id;
	
	private String taskId;
	
	private Long tutorId;
	
	private Long applicationId;
	
	private Set<Long> retainedImgsIdentificationId=new HashSet<>();
	
	private String verifiedTutorInfo; 

	private String adviceToTutor;

	private String retainedIdentification;
	
	
	

}
