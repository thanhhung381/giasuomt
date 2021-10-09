package giasuomt.demo.tutor.dto;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTutorDto extends SaveTutorDto {
	private Long tutorId;
	
	Set<UpdateStudentDto> updateStudentDtos=new HashSet<>();    	                
	 
	private Long idStudentUpdate;
	
	        
	//RegisteredUser
}
