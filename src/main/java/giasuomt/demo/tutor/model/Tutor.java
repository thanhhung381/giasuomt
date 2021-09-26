package giasuomt.demo.tutor.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import giasuomt.demo.commondata.model.User;
import giasuomt.demo.commondata.util.DateUtils;
import giasuomt.demo.job.model.Job;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.task.model.Application;

@Entity
@Table(name = "tutor")
public class Tutor extends User {
	//@Unique
	@NotBlank
	private String tutorCode; //Cần viết tự generate theo dạng 8 số
	
	
//THÔNG TIN CÁ NHÂN
	private String tempAddNo;
	
	private String tempAddSt;
	
	private String tempAddNote;
	
	@ManyToOne
	@JoinColumn(name = "temp_area_id")
	private Area tempArea;

	private String perAddNo;
	
	private String perAddSt;
	
	private String perAddNote;
	
	@ManyToOne
	@JoinColumn(name = "per_area_id")
	private Area perArea;
	
	private String iDNo;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT) //Quy định date format khi nó add đối tượng thành Json để trả về Clients
	@DateTimeFormat(pattern = DateUtils.DATE_FORMAT) //Quy định date format để lưu xuống database
	private LocalDateTime issuedOn;
	
	@ElementCollection(targetClass=String.class)
	private Set<String> infoImg;
	
	
//HIỆN ĐANG LÀ
	@OneToMany(mappedBy = "tutor", fetch = FetchType.EAGER)
	@JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
	private Set<Student> students;
	
	@OneToMany(mappedBy = "tutor", fetch = FetchType.EAGER)
	@JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
	private Set<GraduatedStudent> graduatedStudents;
	
	@OneToMany(mappedBy = "tutor", fetch = FetchType.EAGER)
	@JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
	private Set<InstitutionTeacher> institutionTeachers;	
	
	@OneToMany(mappedBy = "tutor", fetch = FetchType.EAGER)
	@JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
	private Set<SchoolTeacher> schoolTeachers;	
	
//NĂNG LỰC:
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name = "tutor_voice",
	           joinColumns = @JoinColumn(name = "tutor_id"),
	           inverseJoinColumns = @JoinColumn(name = "voice_id"))
	private Set<Voice> voices = new HashSet<>();
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name = "tutor_certificate",
	           joinColumns = @JoinColumn(name = "tutor_id"),
	           inverseJoinColumns = @JoinColumn(name = "certificate_id"))
	private Set<Certificate> certificates = new HashSet<>();
	
	@ElementCollection(targetClass=String.class)
	private Set<String> certificateImg;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name = "tutor_tutor_notice",
	           joinColumns = @JoinColumn(name = "tutor_id"),
	           inverseJoinColumns = @JoinColumn(name = "tutor_notice_id"))
	private Set<TutorNotice> tutorNotices = new HashSet<>();	
	
	private String advantageNote;
	
	

//ĐĂNG KÝ NHẬN LỚP
	@OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
	private Set<Application> applications;
	
//NHẬN LỚP
	@OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
	private Set<Job> jobs;
	

	
//VỊ TRÍ TƯƠNG ĐỐI CỦA GIA SƯ - vị trí này được xác định theo: vị trí các lớp đã nhận (trọng số theo thời gian và số lớp), các lớp đã đăng ký (trọng số theo thời gian và số lớp), nơi ở hiện tại mà gia sư khai báo (trọng số theo thời gian và nơi ở là tạm trú hay thường trú) 
	private String xRelCoo;
		
	private String yRelCoo;
		
	@ManyToOne
	@JoinColumn(name = "rel_area_id")
	private Area relArea;		
	
	

	
	
	
//getter,setter
	
	public String getTempAddNote() {
		return tempAddNote;
	}

	public String getTutorCode() {
		return tutorCode;
	}

	public void setTutorCode(String tutorCode) {
		this.tutorCode = tutorCode;
	}

	public Set<Application> getApplications() {
		return applications;
	}

	public void setApplications(Set<Application> applications) {
		this.applications = applications;
	}

	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

	public void setTempAddNote(String tempAddNote) {
		this.tempAddNote = tempAddNote;
	}

	public String getPerAddNote() {
		return perAddNote;
	}

	public void setPerAddNote(String perAddNote) {
		this.perAddNote = perAddNote;
	}

	public Set<Voice> getVoices() {
		return voices;
	}

	public void setVoices(Set<Voice> voices) {
		this.voices = voices;
	}

	public Set<String> getCertificateImg() {
		return certificateImg;
	}

	public void setCertificateImg(Set<String> certificateImg) {
		this.certificateImg = certificateImg;
	}

	public Set<TutorNotice> getTutorNotices() {
		return tutorNotices;
	}

	public void setTutorNotices(Set<TutorNotice> tutorNotices) {
		this.tutorNotices = tutorNotices;
	}

	public String getAdvantageNote() {
		return advantageNote;
	}

	public void setAdvantageNote(String advantageNote) {
		this.advantageNote = advantageNote;
	}

	public Set<Certificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(Set<Certificate> certificates) {
		this.certificates = certificates;
	}

	public Set<SchoolTeacher> getSchoolTeachers() {
		return schoolTeachers;
	}

	public void setSchoolTeachers(Set<SchoolTeacher> schoolTeachers) {
		this.schoolTeachers = schoolTeachers;
	}

	public Set<GraduatedStudent> getGraduatedStudents() {
		return graduatedStudents;
	}

	public void setGraduatedStudents(Set<GraduatedStudent> graduatedStudents) {
		this.graduatedStudents = graduatedStudents;
	}

	public Set<InstitutionTeacher> getInstitutionTeachers() {
		return institutionTeachers;
	}

	public void setInstitutionTeachers(Set<InstitutionTeacher> institutionTeachers) {
		this.institutionTeachers = institutionTeachers;
	}

	public Set<String> getInfoImg() {
		return infoImg;
	}

	public void setInfoImg(Set<String> infoImg) {
		this.infoImg = infoImg;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public String getiDNo() {
		return iDNo;
	}

	public void setiDNo(String iDNo) {
		this.iDNo = iDNo;
	}

	public LocalDateTime getIssuedOn() {
		return issuedOn;
	}

	public void setIssuedOn(LocalDateTime issuedOn) {
		this.issuedOn = issuedOn;
	}


	public String getxRelCoo() {
		return xRelCoo;
	}

	public void setxRelCoo(String xRelCoo) {
		this.xRelCoo = xRelCoo;
	}

	public String getyRelCoo() {
		return yRelCoo;
	}

	public void setyRelCoo(String yRelCoo) {
		this.yRelCoo = yRelCoo;
	}

	public Area getRelArea() {
		return relArea;
	}

	public void setRelArea(Area relArea) {
		this.relArea = relArea;
	}

	public String getPerAddNo() {
		return perAddNo;
	}

	public void setPerAddNo(String perAddNo) {
		this.perAddNo = perAddNo;
	}

	public String getPerAddSt() {
		return perAddSt;
	}

	public void setPerAddSt(String perAddSt) {
		this.perAddSt = perAddSt;
	}

	public String getTempAddNo() {
		return tempAddNo;
	}

	public void setTempAddNo(String tempAddNo) {
		this.tempAddNo = tempAddNo;
	}

	public String getTempAddSt() {
		return tempAddSt;
	}

	public void setTempAddSt(String tempAddSt) {
		this.tempAddSt = tempAddSt;
	}

	public Area getTempArea() {
		return tempArea;
	}

	public void setTempArea(Area tempArea) {
		this.tempArea = tempArea;
	}

	public Area getPerArea() {
		return perArea;
	}

	public void setPerArea(Area perArea) {
		this.perArea = perArea;
	}
	


}
