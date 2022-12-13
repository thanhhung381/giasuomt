package giasuomt.demo.job.service;

import giasuomt.demo.commondata.generic.IGenericService;


import giasuomt.demo.job.dto.SaveJobDto;
import giasuomt.demo.job.dto.UpdateJobResultDto;
import giasuomt.demo.job.model.Job;

public interface IJobService extends IGenericService<SaveJobDto,Job, String> {
	
	public Job updateJobResult(UpdateJobResultDto dto) throws Exception;

}
