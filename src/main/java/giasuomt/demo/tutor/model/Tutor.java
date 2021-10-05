package giasuomt.demo.tutor.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import giasuomt.demo.commondata.model.RegisteredUser;
import giasuomt.demo.commondata.model.User;
import giasuomt.demo.commondata.util.DateUtils;
import giasuomt.demo.job.model.Job;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.task.model.Application;
import giasuomt.demo.tutor.dto.CreateStudentDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tutor")
public class Tutor extends User {
        //@Unique
//        @NotBlank
        private String tutorCode; //Cần viết tự generate theo dạng 8 số
        
        
//THÔNG TIN CÁ NHÂN
//        private String tempAddNo;
//        
//        private String tempAddSt;
//        
//        private String tempAddNote;
        
        @ManyToOne
        @JoinColumn(name = "temp_area_id")
        private Area tempArea;

//        private String perAddNo;
//        
//        private String perAddSt;
//        
//        private String perAddNote;
//        
//        @ManyToOne
//        @JoinColumn(name = "per_area_id")
//        private Area perArea;
//        
//        private String iDNo;
//        
//        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT) //Quy định date format khi nó add đối tượng thành Json để trả về Clients
//        @DateTimeFormat(pattern = DateUtils.DATE_FORMAT) //Quy định date format để lưu xuống database
//        private LocalDateTime issuedOn;
//        
//        private String infoImgs;
        
        
//HIỆN ĐANG LÀ
        @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//        @JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
        private Set<Student> students;
        
//        @OneToMany(mappedBy = "tutor", fetch = FetchType.EAGER)
//        @JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
//        private Set<GraduatedStudent> graduatedStudents;
//        
//        @OneToMany(mappedBy = "tutor", fetch = FetchType.EAGER)
//        @JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
//        private Set<InstitutionTeacher> institutionTeachers;        
//        
//        @OneToMany(mappedBy = "tutor", fetch = FetchType.EAGER)
//        @JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
//        private Set<SchoolTeacher> schoolTeachers;        
//        
////NĂNG LỰC:
//        private String voices;
//        
//        @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//        @JoinTable(name = "tutor_certificate",
//                   joinColumns = @JoinColumn(name = "tutor_id"),
//                   inverseJoinColumns = @JoinColumn(name = "certificate_id"))
//        private Set<Certificate> certificates = new HashSet<>();
//        
//        private String tutorNotices;        
//        
//        private String advantageNote;
//        
//
////ĐĂNG KÝ NHẬN LỚP
//        @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
//        private Set<Application> applications;
//        
////NHẬN LỚP
//        @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
//        private Set<Job> jobs;
//        
//
//        
////VỊ TRÍ TƯƠNG ĐỐI CỦA GIA SƯ - vị trí này được xác định theo: vị trí các lớp đã nhận (trọng số theo thời gian và số lớp), các lớp đã đăng ký (trọng số theo thời gian và số lớp), nơi ở hiện tại mà gia sư khai báo (trọng số theo thời gian và nơi ở là tạm trú hay thường trú) 
//        private String xRelCoo;
//                
//        private String yRelCoo;
//                
//        @ManyToOne
//        @JoinColumn(name = "rel_area_id")
//        private Area relArea;                
//        
//        
////RegisteredUser
//        @OneToOne(mappedBy = "tutor")
//        private RegisteredUser registeredUser;


        
        
    	public Tutor addTempAreaById(Long tempAreaId) {
    		if(tempAreaId != null) {
    			Area tempArea = new Area();
    			tempArea.setId(tempAreaId);
    			this.tempArea = tempArea; 
    	//		area.getLearnerAndRegisters().add(this);
    		}
    		return this;
    	}  
    	
    	public Tutor addStudent(Set<Student> students) {
    		this.students = students;
    		return this;
    	}
}
