package giasuomt.demo.finance.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.finance.dto.SaveJobFinanceDto;
import giasuomt.demo.finance.model.JobFinance;
import giasuomt.demo.finance.repository.IJobFinanceRepository;
import giasuomt.demo.job.repository.IJobRepository;
import giasuomt.demo.uploadfile.model.BillImage;
import giasuomt.demo.uploadfile.repository.IBillImageRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JobFinanceService extends GenericService<SaveJobFinanceDto, JobFinance, Long> implements IJobFinanceService
{
	
	private IJobRepository iJobRepository;
	
	private IBillImageRepository iBillImageRepository;

	private MapDtoToModel mapDtoToModel;
	
	private IJobFinanceRepository iJobFinanceRepository;


	@Override
	public JobFinance create(SaveJobFinanceDto dto) {
		JobFinance jobFinance = new JobFinance();
		jobFinance.setJob(iJobRepository.getOne(dto.getJobId()));
		
		
		return save(jobFinance,dto);
	}


		
	public JobFinance save(JobFinance jobFinance,SaveJobFinanceDto jobFinanceDto)
	{
		try {
			
			map(jobFinanceDto, jobFinance);
			
			return iJobFinanceRepository.save(jobFinance);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	private void map(SaveJobFinanceDto jobFinanceDto,JobFinance jobFinance)
	{
		jobFinance = (JobFinance)mapDtoToModel.map(jobFinanceDto, jobFinance);
		
		
		
		List<Long> billImagesId=jobFinanceDto.getBillImgsId();
		
		List<String> billImages=new LinkedList<>();
		
		for(int i=0;i<billImagesId.size();i++)
		{
			BillImage billImage=iBillImageRepository.getOne(billImagesId.get(i));
			
			String urlDownload=ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/billImage/downloadFile/")
					.path(billImage.getNameFile()).toUriString();
			
			billImages.add(urlDownload);
			
		}
		jobFinance.setBillImgs(billImages);
		
		
	} 	


	public void delete(Long id)
	{
		try {
			
			iJobFinanceRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	@Override
	public JobFinance update(SaveJobFinanceDto dto) {
	
		JobFinance jobFinance = iJobFinanceRepository.getOne(dto.getId());
		jobFinance.setJob(iJobRepository.getOne(dto.getJobId()));
		
		
		return save(jobFinance,dto);
	}

}
