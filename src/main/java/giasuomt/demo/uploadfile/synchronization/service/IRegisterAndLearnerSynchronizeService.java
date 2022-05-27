package giasuomt.demo.uploadfile.synchronization.service;

import java.util.Set;

import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.person.model.Tutor;

public interface IRegisterAndLearnerSynchronizeService {
	Set<RegisterAndLearner> findAllRegisterAndLearnerSynchronizeSynchronizedAvatarAndPublicAndPrivateImg();
}
