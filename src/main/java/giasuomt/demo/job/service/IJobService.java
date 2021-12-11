package giasuomt.demo.job.service;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.educational.dto.SaveSchoolDto;
import giasuomt.demo.job.dto.SaveJobDto;
import giasuomt.demo.job.model.Job;

public interface IJobService extends IGenericService<SaveJobDto,Job, Long> {

}
