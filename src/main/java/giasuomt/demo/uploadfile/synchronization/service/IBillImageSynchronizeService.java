package giasuomt.demo.uploadfile.synchronization.service;

import java.util.Set;

import giasuomt.demo.finance.model.JobFinance;

public interface IBillImageSynchronizeService {
	Set<JobFinance> findAllJobFinanceSynchronized();
}
