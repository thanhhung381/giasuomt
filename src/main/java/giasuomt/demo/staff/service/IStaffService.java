package giasuomt.demo.staff.service;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.staff.dto.SaveStaffDto;
import giasuomt.demo.staff.dto.UpdateAvatarStaff;
import giasuomt.demo.staff.model.Staff;

public interface IStaffService extends IGenericService<SaveStaffDto,Staff, Long> {
	public	Staff updateAvatarStaff(UpdateAvatarStaff dto);
}
