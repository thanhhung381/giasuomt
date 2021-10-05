package giasuomt.demo.tutor.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import giasuomt.demo.location.dto.CreateAreaDTO;
import giasuomt.demo.location.dto.FindingDtoArea;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.tutor.model.Student;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CreateTutorDto {
	
		private String tutorCode; //Cần viết tự generate theo dạng 8 số
    
    
	//THÔNG TIN CÁ NHÂN
	        private String tempAddNo;
	        
	        private String tempAddSt;
	        
	        private String tempAddNote;
	        
	     
	        
	        
	   //     private FindingDtoArea   tempArea;
	    	private Long tempArea;
	   
	    	
	        private String perAddNo;
	        
	        private String perAddSt;
	        
	        private String perAddNote;
	        
	        
	       
	  //      private FindingDtoArea perArea;      
	        private Long perArea;
	        
	        private String iDNo;
	        
	     
	        private LocalDateTime issuedOn;
	        
	        private String infoImgs;
	        
	        
	//HIỆN ĐANG LÀ
	     
	        
	      
	        
	//NĂNG LỰC:
	        private String voices;
	        
	     //   Set<Student> student=new HashSet<Student>();
	        
	 //       CreateStudentDto students;
	        
	        private String tutorNotices;        
	        
	        private String advantageNote;
	        

	//ĐĂNG KÝ NHẬN LỚP
	       
	        
	//NHẬN LỚP
	        
	        

	        
	//VỊ TRÍ TƯƠNG ĐỐI CỦA GIA SƯ - vị trí này được xác định theo: vị trí các lớp đã nhận (trọng số theo thời gian và số lớp), các lớp đã đăng ký (trọng số theo thời gian và số lớp), nơi ở hiện tại mà gia sư khai báo (trọng số theo thời gian và nơi ở là tạm trú hay thường trú) 
	        private String xRelCoo;
	                
	        private String yRelCoo;
	                
	        private Long relArea;
	    //
	        //private FindingDtoArea  relArea;
	    	
	    	
	    	                
	        private String studentId;
	        
	//RegisteredUser
}
