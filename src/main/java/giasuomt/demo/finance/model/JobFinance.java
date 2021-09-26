package giasuomt.demo.finance.model;

import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.finance.util.JobFinanceType;
import giasuomt.demo.finance.util.UnitOfMoney;
import giasuomt.demo.finance.util.WayOfPaying;
import giasuomt.demo.job.model.Job;

@Entity
@Table(name = "job_finance")
public class JobFinance extends AbstractEntity {
	private JobFinanceType type;
	
	private WayOfPaying wayOfPaying;
	
	@ElementCollection(targetClass=String.class)
	private Set<String> billImg;
	
	private int amountOfMoney;
	
	private UnitOfMoney unitOfMoney;
	
	private String note;

	@ManyToOne
	@JoinColumn(name = "job_id")
	private Job job;
	
	//STK (nếu có) - thường dùng cho yêu cầu hoàn phí
	private String accountNumber;
	
	private String accoundBank;
	
	private String accountName;
	
	
	
	//getter,setter
	
	public JobFinanceType getType() {
		return type;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccoundBank() {
		return accoundBank;
	}

	public void setAccoundBank(String accoundBank) {
		this.accoundBank = accoundBank;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public void setType(JobFinanceType type) {
		this.type = type;
	}

	public WayOfPaying getWayOfPaying() {
		return wayOfPaying;
	}

	public void setWayOfPaying(WayOfPaying wayOfPaying) {
		this.wayOfPaying = wayOfPaying;
	}

	public Set<String> getBillImg() {
		return billImg;
	}

	public void setBillImg(Set<String> billImg) {
		this.billImg = billImg;
	}

	public int getAmountOfMoney() {
		return amountOfMoney;
	}

	public void setAmountOfMoney(int amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}

	public UnitOfMoney getUnitOfMoney() {
		return unitOfMoney;
	}

	public void setUnitOfMoney(UnitOfMoney unitOfMoney) {
		this.unitOfMoney = unitOfMoney;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
	
}
