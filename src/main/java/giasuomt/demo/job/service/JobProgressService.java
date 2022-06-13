package giasuomt.demo.job.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.job.dto.SaveJobProgressDto;
import giasuomt.demo.job.model.JobProgress;
import giasuomt.demo.job.repository.IJobProgressRepository;
import giasuomt.demo.job.repository.IJobRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JobProgressService extends GenericService<SaveJobProgressDto, JobProgress, Long> implements IJobProgressServce {
	
	
	private IJobProgressRepository iJobProgressRepository;
	
	private IJobRepository iJobRepository;
	
	private MapDtoToModel mapDtoToModel;
	
	
	public JobProgress create(SaveJobProgressDto dto) {
		
		JobProgress jobProgress=new JobProgress();
		
		jobProgress.setJob(iJobRepository.getOne(dto.getIdJob()));
		
		return save(dto,jobProgress);
	}
	
	
	public JobProgress save(SaveJobProgressDto dto,JobProgress jobProgress)
	{
		try {
			
			map(dto, jobProgress);
			
			
			return iJobProgressRepository.save(jobProgress);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return  null;
		
	}
	
	
	
	private void map(SaveJobProgressDto dto,JobProgress jobProgress)
	{
		jobProgress=(JobProgress)mapDtoToModel.map(dto, jobProgress);
		
		
		
	}
	

	@Override
	public JobProgress update(SaveJobProgressDto dto) {
		
	JobProgress jobProgress=iJobProgressRepository.getOne(dto.getId());
		
		jobProgress.setJob(iJobRepository.getOne(dto.getIdJob()));
		
		return save(dto,jobProgress);
	}

}
