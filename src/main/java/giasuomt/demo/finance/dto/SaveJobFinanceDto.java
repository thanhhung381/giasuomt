package giasuomt.demo.finance.dto;

import giasuomt.demo.finance.util.JobFinanceType;
import giasuomt.demo.finance.util.UnitOfMoney;
import giasuomt.demo.finance.util.WayOfPaying;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveJobFinanceDto {
	private Long id;	
	private JobFinanceType type;	
	private WayOfPaying wayOfPaying;		
	private Integer  amountOfMoney;	
	private UnitOfMoney unitOfMoney;	
	private String note;
	private String  jobId;	
	//STK (nếu có) - thường dùng cho yêu cầu hoàn phí
	private String accountNumber;
	private String accoundBank;
	private String accountName;
}
