package giasuomt.demo.location.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveRegisterAndLearnerAddressDto {
	private Long id;
	private String addType;	
	private String addNumber;	
	private String addStreet;	
	private String addNote;	
	private String xCoo;	
	private String yCoo;	
	private String idArea;
}
