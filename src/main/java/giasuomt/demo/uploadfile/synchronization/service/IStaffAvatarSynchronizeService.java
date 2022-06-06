package giasuomt.demo.uploadfile.synchronization.service;

import java.util.Set;

import giasuomt.demo.staff.model.Staff;
import giasuomt.demo.user.model.User;

public interface IStaffAvatarSynchronizeService {
	
	Set<Staff> findAllStaffSynchronized();
	
}
