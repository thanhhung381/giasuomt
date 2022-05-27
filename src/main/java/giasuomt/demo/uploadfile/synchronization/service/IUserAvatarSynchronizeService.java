package giasuomt.demo.uploadfile.synchronization.service;

import java.util.Set;

import giasuomt.demo.user.model.User;

public interface IUserAvatarSynchronizeService {
	
	Set<User> findAllUserSynchronized();
	
}
