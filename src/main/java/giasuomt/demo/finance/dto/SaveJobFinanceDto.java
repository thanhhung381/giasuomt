package giasuomt.demo.finance.dto;

import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import giasuomt.demo.finance.util.JobFinanceType;
import giasuomt.demo.finance.util.UnitOfMoney;
import giasuomt.demo.finance.util.WayOfPaying;
import giasuomt.demo.job.model.Job;
import lombok.Getter;

@Getter
@Setter
public class SaveJobFinanceDto {
	
	
	private Long id;
	
	private JobFinanceType type;
	

	private WayOfPaying wayOfPaying;
	
	
	private int amountOfMoney;
	
	private UnitOfMoney unitOfMoney;
	
	private String note;


	private Long  jobId;
	
	//STK (nếu có) - thường dùng cho yêu cầu hoàn phí
	private String accountNumber;
	
	private String accoundBank;
	
	private String accountName;
}
