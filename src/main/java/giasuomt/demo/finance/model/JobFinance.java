package giasuomt.demo.finance.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.finance.util.JobFinanceType;
import giasuomt.demo.finance.util.UnitOfMoney;
import giasuomt.demo.finance.util.WayOfPaying;
import giasuomt.demo.job.model.Job;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "job_finance")
@Getter
@Setter
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class JobFinance extends AbstractEntity {
	
	@Enumerated(EnumType.STRING)
	private JobFinanceType type;
	
	@Enumerated(EnumType.STRING)
	private WayOfPaying wayOfPaying;
	
	@ElementCollection
	private List<String> billImgs = new LinkedList<>();
	
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
	
	

	
}
