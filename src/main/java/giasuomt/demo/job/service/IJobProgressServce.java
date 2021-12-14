package giasuomt.demo.job.service;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.job.dto.SaveJobProgressDto;
import giasuomt.demo.job.model.JobProgress;

public interface IJobProgressServce extends IGenericService<SaveJobProgressDto, JobProgress, Long> {

}
