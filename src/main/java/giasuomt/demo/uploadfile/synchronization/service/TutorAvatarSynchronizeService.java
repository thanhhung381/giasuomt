package giasuomt.demo.uploadfile.synchronization.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.person.repository.ITutorRepository;
import giasuomt.demo.uploadfile.service.IAvatarAwsService;
import giasuomt.demo.uploadfile.service.IAvatarAndPublicAndPrivateImgsTutorAwsService;
import giasuomt.demo.user.repository.IUserRepository;

@Service
public class TutorAvatarSynchronizeService implements ITutorAvatarSynchronizeService {
	@Autowired
	private IAvatarAndPublicAndPrivateImgsTutorAwsService iAvatarTutorAwsService;

	@Autowired
	private ITutorRepository iTutorRepository;

	@Override
	public Set<Tutor> findAllTutorSynchronizedAvatarAndPublicAndPrivateImg() {

		// Avatar
		Set<String> urlAvatarTutors = Sets.newHashSet(iAvatarTutorAwsService.findAll());

		Set<String> urlTutorPrivateImgs = Sets.newHashSet(iAvatarTutorAwsService.findAllPrivateImgs());

		Set<String> urlTutorPublicImgs = Sets.newHashSet(iAvatarTutorAwsService.findAllPublicImgs());

		Set<Tutor> tutors = iTutorRepository.findTutorBeforeSynchronize();

		for (Tutor tutor : tutors) {
			for (String urlAvatarTutor : urlAvatarTutors) {
				boolean check = true;
				if (!tutor.getAvatar().contains(urlAvatarTutor.substring(urlAvatarTutor.lastIndexOf("/") + 1))) {
					check = false;
				}

				if (check) {
					tutor.setAvatar(urlAvatarTutor);

				}
			}
			// privateImgs
			Set<String> synchronizePrivateImgs = tutor.getPrivateImgs();
			synchronizePrivateImgs.clear();
			for (String url : urlTutorPrivateImgs) {
				boolean check = true;
				
				if (!url.contains(String.valueOf(tutor.getId()))) {
					check = false;
				}

				if (check) {

					synchronizePrivateImgs.add(url);

					tutor.setPrivateImgs(synchronizePrivateImgs);

				}
			}

			Set<String> synchronizePublicImgs = tutor.getPublicImgs();

			synchronizePublicImgs.clear();
			for (String url : urlTutorPublicImgs) {
				boolean check = true;
				if (!url.contains(String.valueOf(tutor.getId()))) {
					check = false;
				}

				if (check) {

					synchronizePublicImgs.add(url);

					tutor.setPublicImgs(synchronizePublicImgs);

				}
			}

			iTutorRepository.save(tutor);

		}

		return Sets.newHashSet(iTutorRepository.findAll());
	}

	private List<String> removeDuplicateElemet(List<String> string) {
		List<String> temp = new LinkedList<>();

		for (int i = 0; i < string.size(); i++) {
			boolean check = false;
			for (int j = 0; j < i; j++) {
				if (string.get(i).contains(
						string.get(j).substring(string.get(j).lastIndexOf("/"), string.get(j).lastIndexOf("P")))) {
					check = true;
					break;
				}
			}

			if (check == false) {

				for (int j = i + 1; j < string.size(); j++) {
					if (string.get(i).contains(string.get(j))) {

						string.remove(j);
						j--;
					}
				}
				temp.add(string.get(i));
			}

		}

		return temp;
	}
}
