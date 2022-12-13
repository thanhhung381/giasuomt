package giasuomt.demo.job.dto;

import giasuomt.demo.job.model.JobResult;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateJobResultDto {
	private String id;
	private JobResult jobResult;
	private String failReason;
//(Tìm thêm gia sư nếu fail?) YES or NO
	private Boolean findAnotherTutorIfFail;
}
