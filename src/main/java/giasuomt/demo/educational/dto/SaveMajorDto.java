package giasuomt.demo.educational.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveMajorDto {
	
	private Long id;
	
	private String name;
    
    private String code;
    
    private Long idUniversity;
}
