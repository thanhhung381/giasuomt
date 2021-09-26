package giasuomt.demo.learnerAndRegister.dto;

import giasuomt.demo.commondata.dto.SaveUserDTO_Staff;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveLearnerAndRegisterDTO_Staff extends SaveUserDTO_Staff{
	private String addNo;
	
	private String addSt;
	
	private String addNote;
	
	private Long areaId;

	private String note;
	
	private String relationship;

}
