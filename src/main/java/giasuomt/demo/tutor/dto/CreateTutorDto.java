package giasuomt.demo.tutor.dto;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import giasuomt.demo.commondata.util.DateUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTutorDto {
	private String tutorCode;
	
	
    private String tempAddNo;
    
    private String tempAddSt;
    
    private String tempAddNote;
    
    private Long tempAreaId;
    
    
    private String perAddNo;
    
    private String perAddSt;
    
    private String perAddNote;
    
    private Long perAreaId;
    
    
    private String iDNo;
    
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT) //Quy định date format để lưu xuống database
    private LocalDateTime issuedOn;
    
    
    private String infoImgs;
    
    
  //HIỆN ĐANG LÀ
    private Set<CreateStudentDto> students;
    
   
    
 
}
