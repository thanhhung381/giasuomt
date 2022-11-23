package giasuomt.demo.finance.service;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.finance.dto.SaveJobFinanceDto;
import giasuomt.demo.finance.model.JobFinance;


public interface IJobFinanceService extends IGenericService<SaveJobFinanceDto, JobFinance, Long> {
	public JobFinance create(SaveJobFinanceDto dto);
	public void delete(Long id);
	public JobFinance update(SaveJobFinanceDto dto);
	
}
