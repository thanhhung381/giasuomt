package giasuomt.demo.job.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.finance.model.JobFinance;
import giasuomt.demo.task.model.Application;
import giasuomt.demo.task.model.Task;

@Entity
@Table(name = "job")
public class Job extends AbstractEntity {
	@ManyToOne
	@JoinColumn(name = "task_id")
	private Task task;
	
	@ManyToOne
	@JoinColumn(name = "tutor_id")
	private Task tutor;
	
	//Lấy application để lấy comment
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "application_id")
	private Application application;
	
	
//Thông tin Task và Tutor tại thời điểm giao (để lưu trữ lại)
	private String taskOldJson;
	
	private String tutorOldJson;
	
//Link GGT, Link HĐ, Hình thức giao (hợp đồng đc kí tại)
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "referral_id")
	private Referral referral;
	
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "contract_id")
	private Contract contract;
	
	private String contractSignAt; //Online, Offline, hoặc tại địa chỉ chi nhánh nào đó
	
//Nhân viên giao lớp đã xác minh, và dặn dò Tutor tại thời điểm giao:
	private String verifiedTutorInfo; //- Đã xác nhận: hình, CMND, thẻ SV, thẻ GV, bằng cấp, chứng chỉ, thành tích, kinh nghiệm, năng lực
	
	private String adviceToTutor; //Đã dặn dò:gọi PH ngay, gọi xong báo, báo sau khi dạy 1 buổi đầu, báo ngay khi có vấn đề
	
//Giấy tờ giữ lại
	private String retainedIdentification;
	

//TÀI CHÍNH	
	@OneToMany(mappedBy = "job", fetch = FetchType.EAGER)
	private Set<JobFinance> jobFinances;
		
	
//TÌNH HÌNH
	@OneToMany(mappedBy = "job", fetch = FetchType.EAGER)
	private Set<JobProgress> jobProgresses;
	
	
//KẾT QUẢ
	private String jobResult;
	
//(Tìm thêm gia sư nếu fail?) YES or NO
	private boolean findAnotherTutorIfFail;
	
	
//REVIEWS
	@OneToMany(mappedBy = "job", fetch = FetchType.EAGER)
	private Set<JobReview> jobReviews;
	

	
	

	
	
	
//getter,setter
	
	public Task getTask() {
		return task;
	}

	public boolean isFindAnotherTutorIfFail() {
		return findAnotherTutorIfFail;
	}

	public void setFindAnotherTutorIfFail(boolean findAnotherTutorIfFail) {
		this.findAnotherTutorIfFail = findAnotherTutorIfFail;
	}

	public String getAdviceToTutor() {
		return adviceToTutor;
	}

	public void setAdviceToTutor(String adviceToTutor) {
		this.adviceToTutor = adviceToTutor;
	}

	public Set<JobFinance> getJobFinances() {
		return jobFinances;
	}

	public void setJobFinances(Set<JobFinance> jobFinances) {
		this.jobFinances = jobFinances;
	}

	public Set<JobProgress> getJobProgresses() {
		return jobProgresses;
	}

	public void setJobProgresses(Set<JobProgress> jobProgresses) {
		this.jobProgresses = jobProgresses;
	}

	public String getJobResult() {
		return jobResult;
	}

	public void setJobResult(String jobResult) {
		this.jobResult = jobResult;
	}

	public Set<JobReview> getJobReviews() {
		return jobReviews;
	}

	public void setJobReviews(Set<JobReview> jobReviews) {
		this.jobReviews = jobReviews;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Task getTutor() {
		return tutor;
	}

	public void setTutor(Task tutor) {
		this.tutor = tutor;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getTaskOldJson() {
		return taskOldJson;
	}

	public void setTaskOldJson(String taskOldJson) {
		this.taskOldJson = taskOldJson;
	}

	public String getTutorOldJson() {
		return tutorOldJson;
	}

	public void setTutorOldJson(String tutorOldJson) {
		this.tutorOldJson = tutorOldJson;
	}

	public Referral getReferral() {
		return referral;
	}

	public void setReferral(Referral referral) {
		this.referral = referral;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getContractSignAt() {
		return contractSignAt;
	}

	public void setContractSignAt(String contractSignAt) {
		this.contractSignAt = contractSignAt;
	}

	public String getVerifiedTutorInfo() {
		return verifiedTutorInfo;
	}

	public void setVerifiedTutorInfo(String verifiedTutorInfo) {
		this.verifiedTutorInfo = verifiedTutorInfo;
	}

	public String getRetainedIdentification() {
		return retainedIdentification;
	}

	public void setRetainedIdentification(String retainedIdentification) {
		this.retainedIdentification = retainedIdentification;
	}

}
