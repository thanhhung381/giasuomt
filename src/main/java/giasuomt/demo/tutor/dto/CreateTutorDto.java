package giasuomt.demo.tutor.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import giasuomt.demo.commondata.dto.CreateUserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTutorDto extends CreateUserDto{
	
	private String tutorCode; // Cần viết tự generate theo dạng 8 số

	// THÔNG TIN CÁ NHÂN
	private String tempAddNo;

	private String tempAddSt;

	private String tempAddNote;

	private Long tempAreaId;

	private String perAddNo;

	private String perAddSt;

	private String perAddNote;

	private Long perAreaId;

	private String iDNo;

	private LocalDateTime issuedOn;

	private String infoImgs;

	// HIỆN ĐANG LÀ

	// NĂNG LỰC:
	private String voices;

	private String tutorNotices;

	private String advantageNote;

	// ĐĂNG KÝ NHẬN LỚP

	// NHẬN LỚP

	// VỊ TRÍ TƯƠNG ĐỐI CỦA GIA SƯ - vị trí này được xác định theo: vị trí các lớp
	// đã nhận (trọng số theo thời gian và số lớp), các lớp đã đăng ký (trọng số
	// theo thời gian và số lớp), nơi ở hiện tại mà gia sư khai báo (trọng số theo
	// thời gian và nơi ở là tạm trú hay thường trú)
	private String xRelCoo;

	private String yRelCoo;

	private Long relAreaId;
	
	
	
	    	
	    	                
	 //Lưu Student 
	private Set<CreateStudentDto> createStudentDtos=new HashSet<>();
	//Lưu GraduatedStudent
	private Set<CreateGraduatedStudentDto> createGraduatedStudentDtos=new HashSet<>();
	//luu Institution Teacher        
	private Set<CreateInstitutionTeacherDto> createInstitutionTeacherDtos=new HashSet<>();
	//luu School Teacher
	private Set<CreateSchoolTeacherDto> createSchoolTeacherDtos=new HashSet<>();
	//RegisteredUser
}
