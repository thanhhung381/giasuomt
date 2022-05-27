package giasuomt.demo.uploadfile.synchronization.service;

import java.util.Set;

import giasuomt.demo.finance.model.JobFinance;
import giasuomt.demo.job.model.Job;

public interface IRetainedImgsIdentificationSynchronizeService {
	Set<Job> findAllJobRetainedImgsIdentificationSynchronized();
}
