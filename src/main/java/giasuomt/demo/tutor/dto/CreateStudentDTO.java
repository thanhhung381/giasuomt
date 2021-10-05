package giasuomt.demo.tutor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudentDTO {
    private String confirmImgs;
    
    private String nowLevel;
    
    private Long institutionId;
    
    private Long majorId;
    
    private String anotherMajor;
}
