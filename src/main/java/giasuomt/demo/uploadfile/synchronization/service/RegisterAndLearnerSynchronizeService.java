package giasuomt.demo.uploadfile.synchronization.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.person.repository.IRegisterAndLearnerRepository;
import giasuomt.demo.uploadfile.service.IAvatarAndPublicAndPrivateRegisterAndLearnerAwsService;

@Service
public class RegisterAndLearnerSynchronizeService implements IRegisterAndLearnerSynchronizeService {

	@Autowired
	private IAvatarAndPublicAndPrivateRegisterAndLearnerAwsService iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService;

	@Autowired
	private IRegisterAndLearnerRepository iRegisterAndLearnerRepository;

	@Override
	public Set<RegisterAndLearner> findAllRegisterAndLearnerSynchronizeSynchronizedAvatarAndPublicAndPrivateImg() {

		Set<String> urlAvatarRegisterAndLearners = Sets
				.newHashSet(iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.findAll());

		Set<String> urlRegisterAndLearnerPrivateImgs = Sets
				.newHashSet(iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.findAllPrivateImgs());

		Set<String> urlRegisterAndLearnerPublicImgs = Sets
				.newHashSet(iAvatarAndPublicAndPrivateRegisterAndLearnerAwsService.findAllPublicImgs());

		Set<RegisterAndLearner> registerAndLearners = iRegisterAndLearnerRepository.findTutorBeforeSynchronize();

		for (RegisterAndLearner registerAndLearner : registerAndLearners) {
			for (String urlAvatarRegisterAndLearner : urlAvatarRegisterAndLearners) {
				boolean check = true;
				if (!registerAndLearner.getAvatar().contains(
						urlAvatarRegisterAndLearner.substring(urlAvatarRegisterAndLearner.lastIndexOf("/") + 1))) {
					check = false;
				}

				if (check) {
					registerAndLearner.setAvatar(urlAvatarRegisterAndLearner);

				}
			}
			// privateImgs
			Set<String> synchronizePrivateImgs = registerAndLearner.getPrivateImgs();
			synchronizePrivateImgs.clear();
			for (String url : urlRegisterAndLearnerPrivateImgs) {
				boolean check = true;

				if (!url.contains(String.valueOf(registerAndLearner.getId() + "Private"))) {
					check = false;
				}

				if (check) {

					synchronizePrivateImgs.add(url);

					registerAndLearner.setPrivateImgs(synchronizePrivateImgs);

				}
			}

			Set<String> synchronizePublicImgs = registerAndLearner.getPublicImgs();

			synchronizePublicImgs.clear();
			for (String url : urlRegisterAndLearnerPublicImgs) {
				boolean check = true;
				if (!url.contains(String.valueOf(registerAndLearner.getId()) + "Public")) {
					check = false;
				}

				if (check) {

					synchronizePublicImgs.add(url);

					registerAndLearner.setPublicImgs(synchronizePublicImgs);

				}
			}

			iRegisterAndLearnerRepository.save(registerAndLearner);

		}

		return Sets.newHashSet(iRegisterAndLearnerRepository.findAll());

	}

}
