package giasuomt.demo.educational.dto;

import giasuomt.demo.commondata.generic.DtoId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveSubjectGroupDto extends DtoId {
	
	
	
    private String name;
	
	private String shortName;
}
