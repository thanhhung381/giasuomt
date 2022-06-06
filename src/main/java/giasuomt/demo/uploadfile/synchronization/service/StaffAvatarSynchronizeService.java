package giasuomt.demo.uploadfile.synchronization.service;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import giasuomt.demo.staff.model.Staff;
import giasuomt.demo.staff.repository.IStaffRepository;
import giasuomt.demo.uploadfile.service.IAvatarAwsService;
import giasuomt.demo.user.model.User;
import giasuomt.demo.user.repository.IUserRepository;
import giasuomt.demo.user.service.IUserService;

@Service
public class StaffAvatarSynchronizeService implements IStaffAvatarSynchronizeService {

	@Autowired
	private IAvatarAwsService iAvatarAwsService;
	
	@Autowired
	private IStaffRepository iStaffRepository;
	
	@Override
	public Set<Staff> findAllStaffSynchronized() {
		
		try {
			Set<String> avatarUserURL=Sets.newHashSet(iAvatarAwsService.findAll()); // list all url
			
			Set<Staff> staffs=Sets.newHashSet(iStaffRepository.findAllStaffSynchronized());
			
			for (Staff staff : staffs) {
				for(String url : avatarUserURL )
				{
					boolean check=true;
					
					if(!String.valueOf(staff.getId()).equals(url.substring(url.lastIndexOf("/")+1)))
					{
						check=false; // khac
					}
					
					if(check)
					{
						staff.setAvatar(url);
						
						iStaffRepository.save(staff);
					}
				}
			}
			
			return Sets.newHashSet(iStaffRepository.findAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}

}
