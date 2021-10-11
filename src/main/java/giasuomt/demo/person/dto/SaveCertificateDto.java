package giasuomt.demo.person.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveCertificateDto {
	
	private Long id;
	
	private String certificateName;
	
	private String certificateType;
	
	private String description;
}
