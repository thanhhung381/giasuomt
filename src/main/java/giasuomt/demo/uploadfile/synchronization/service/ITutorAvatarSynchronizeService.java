package giasuomt.demo.uploadfile.synchronization.service;

import java.util.Set;

import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.user.model.User;

public interface ITutorAvatarSynchronizeService {

	Set<Tutor> findAllTutorSynchronizedAvatarAndPublicAndPrivateImg();
}
