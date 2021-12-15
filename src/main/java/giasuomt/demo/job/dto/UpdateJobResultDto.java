package giasuomt.demo.job.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateJobResultDto {
	
	
	private Long id;
	
	private String jobResult;
	
	private String failReason;
	
//(Tìm thêm gia sư nếu fail?) YES or NO
	private Boolean findAnotherTutorIfFail;
}
