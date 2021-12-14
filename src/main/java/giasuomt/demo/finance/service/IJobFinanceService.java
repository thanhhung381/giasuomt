package giasuomt.demo.finance.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.finance.dto.SaveJobFinanceDto;
import giasuomt.demo.finance.model.JobFinance;
import giasuomt.demo.uploadfile.model.BillImage;

public interface IJobFinanceService extends IGenericService<SaveJobFinanceDto, JobFinance, Long> {

	public JobFinance create(SaveJobFinanceDto dto);
	

		

	

	public void delete(Long id);

	
	public JobFinance update(SaveJobFinanceDto dto);
	
}
