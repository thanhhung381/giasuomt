package giasuomt.demo.educational.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveSchoolDto {
	
	private Long id;
	
    private String name;
    
    private String nameAbbr;
    
    private String code;
    
    private String schoolTypes;
}
