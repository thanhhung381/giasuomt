package giasuomt.demo.job.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveJobDto {
	private String id;
	private String applicationId;
	private String verifiedTutorInfo;
	private String adviceToTutor;

}
