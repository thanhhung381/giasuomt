package giasuomt.demo.person.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveTutorInvitationDto {

	private Long id;
	
	private Long registerAndLearnerId;

	private String learnerAndReqisterPhone;
	
	private Long tutorId;
	
	private String note;
}
