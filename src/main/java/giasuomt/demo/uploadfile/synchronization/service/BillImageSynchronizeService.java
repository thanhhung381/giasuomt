package giasuomt.demo.uploadfile.synchronization.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import giasuomt.demo.finance.model.JobFinance;
import giasuomt.demo.finance.repository.IJobFinanceRepository;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.uploadfile.service.IBillImageAwsService;
import giasuomt.demo.user.model.User;

@Service
public class BillImageSynchronizeService implements IBillImageSynchronizeService {

	@Autowired
	private IBillImageAwsService iBillImageAwsService;

	@Autowired
	private IJobFinanceRepository iJobFinanceRepository;

	@Override
	public Set<JobFinance> findAllJobFinanceSynchronized() {

		try {
			Set<String> avatarUserURL = Sets.newHashSet(iBillImageAwsService.findAll()); // list all url

			Set<JobFinance> jobFinances = Sets.newHashSet(iJobFinanceRepository.findAllJobFinanceSynchronized());

			for (JobFinance job : jobFinances) {
				for (String url : avatarUserURL) {
					boolean check = true;

					if (job.getId() != Long.parseLong(url.substring(url.lastIndexOf("/") + 1))) {
						check = false; // khac
					}

					if (check) {
						job.setBillImg(url);

						iJobFinanceRepository.save(job);
					}
				}
			}

			return Sets.newHashSet(iJobFinanceRepository.findAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
