package giasuomt.demo.job.model;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.finance.model.JobFinance;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.task.model.Application;
import giasuomt.demo.task.model.Task;
import lombok.Setter;
import lombok.Getter;

@Entity
@Table(name = "job")
@Getter
@Setter
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class Job extends AbstractEntity {
	
	@ManyToOne
	@JoinColumn(name = "task_id")
	@JsonIgnore
	private Task task;

	@ManyToOne
	@JoinColumn(name = "tutor_id")
	private Tutor tutor;

	// Lấy application để lấy comment
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "application_id", referencedColumnName = "id")
	private Application application;
	

//Thông tin Task và Tutor tại thời điểm giao (để lưu trữ lại)	
	@OneToOne(fetch = FetchType.LAZY)
	private TaskByTheTimeCreatingJob taskByTheTimeCreatingJob;
	
	@OneToOne(fetch = FetchType.LAZY)
	private TutorByTheTimeCreatingJob tutorByTheTimeCreatingJob; 
	
	

//Link GGT, Link HĐ, Hình thức giao (hợp đồng đc kí tại)
//	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//	@JoinColumn(name = "referral_id")
//	private Referral referral;

//	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//	@JoinColumn(name = "contract_id")
//	private Contract contract;

//	private String contractSignAt; //Online, Offline, hoặc tại địa chỉ chi nhánh nào đó

//Nhân viên giao lớp đã xác minh, và dặn dò Tutor tại thời điểm giao:
	private String verifiedTutorInfo; //- Đã xác nhận: hình, CMND, thẻ SV, thẻ GV, bằng cấp, chứng chỉ, thành tích, kinh nghiệm, năng lực

	private String adviceToTutor; //Đã dặn dò:gọi PH ngay, gọi xong báo, báo sau khi dạy 1 buổi đầu, báo ngay khi có vấn đề

//Giấy tờ giữ lại
	
	private String retainedIdentification;
	
	@ElementCollection // nếu sử dụng kiểu dữ liệu List vâng vâng
	private List<String> retainedImgsIdentification=new LinkedList<>();

//TÀI CHÍNH	
	@OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
	private List<JobFinance> jobFinances=new LinkedList<>();

//TÌNH HÌNH
	@OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
//	@Fetch(FetchMode.SUBSELECT)
	private List<JobProgress> jobProgresses=new LinkedList<>();

//KẾT QUẢ
	private String jobResult;
	
	private String failReason;
	
//(Tìm thêm gia sư nếu fail?) YES or NO
	private boolean findAnotherTutorIfFail ;

//REVIEWS
	@OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
	private List<JobReview> jobReviews;



}
