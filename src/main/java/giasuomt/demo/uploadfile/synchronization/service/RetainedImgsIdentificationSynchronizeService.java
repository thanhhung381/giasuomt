package giasuomt.demo.uploadfile.synchronization.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import giasuomt.demo.job.model.Job;
import giasuomt.demo.job.repository.IJobRepository;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.uploadfile.service.IRetainedImgsIdentificationAwsService;
@Service
public class RetainedImgsIdentificationSynchronizeService implements IRetainedImgsIdentificationSynchronizeService {

	@Autowired
	private IRetainedImgsIdentificationAwsService iRetainedImgsIdentificationAwsService;
	
	
	@Autowired
	private IJobRepository iJobRepository;
	
	
	@Override
	public Set<Job> findAllJobRetainedImgsIdentificationSynchronized() {
		// Avatar
				Set<String> urlRetainedImgsIdentificationOfJob = Sets.newHashSet(iRetainedImgsIdentificationAwsService.findAll());

				Set<Job> jobs = Sets.newHashSet(iJobRepository.findAll());

				for (Job job : jobs) {
			
					// privateImgs
					Set<String> synchronizeRetainedImgsIdentifications = job.getRetainedImgsIdentification();
					synchronizeRetainedImgsIdentifications.clear();
					for (String url : urlRetainedImgsIdentificationOfJob) {
						boolean check = true;
						
						if (!url.contains(String.valueOf(job.getId())+"RetainedImgsIdentification")) {
							check = false;
						}

						if (check) {

							synchronizeRetainedImgsIdentifications.add(url);

							job.setRetainedImgsIdentification(synchronizeRetainedImgsIdentifications);

						}
					}

			

					iJobRepository.save(job);

				}

				return Sets.newHashSet(iJobRepository.findAll());
	}

}
