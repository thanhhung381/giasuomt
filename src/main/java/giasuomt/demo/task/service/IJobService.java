package giasuomt.demo.task.service;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.job.model.Job;
import giasuomt.demo.task.dto.SaveJobDto;

public interface IJobService extends IGenericService<SaveJobDto,Job,Long> {

}
