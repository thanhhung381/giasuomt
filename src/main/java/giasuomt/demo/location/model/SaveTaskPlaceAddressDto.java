package giasuomt.demo.location.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveTaskPlaceAddressDto {
	
	
	private Long id;
	
	private String exactAddNumber;
	
	private String exactAddStreet;
	
	private String exactAddNote;
	
	private String exactXCoo;
	
	private String exactYCoo;
	
	
	private String relAddNumber;
	
	private String relAddStreet;	
	
	private String relAddNote;
	
	private String relXCoo;
	
	private String relYCoo;
	
	
	private Long idArea;
}
