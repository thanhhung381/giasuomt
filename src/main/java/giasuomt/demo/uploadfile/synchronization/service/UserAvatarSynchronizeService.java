package giasuomt.demo.uploadfile.synchronization.service;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import giasuomt.demo.uploadfile.service.IAvatarAwsService;
import giasuomt.demo.user.model.User;
import giasuomt.demo.user.repository.IUserRepository;
import giasuomt.demo.user.service.IUserService;

@Service
public class UserAvatarSynchronizeService implements IUserAvatarSynchronizeService {

	@Autowired
	private IAvatarAwsService iAvatarAwsService;
	
	@Autowired
	private IUserRepository iUserRepository;
	
	@Override
	public Set<User> findAllUserSynchronized() {
		
		try {
			Set<String> avatarUserURL=Sets.newHashSet(iAvatarAwsService.findAll()); // list all url
			
			Set<User> users=Sets.newHashSet(iUserRepository.findAllUserSynchronized());
			
			for (User user : users) {
				for(String url : avatarUserURL )
				{
					boolean check=true;
					
					if(!user.getUsername().equals(url.substring(url.lastIndexOf("/")+1, url.lastIndexOf("Avatar"))))
					{
						check=false; // khac
					}
					
					if(check)
					{
						user.setAvatar(url);
						
						iUserRepository.save(user);
					}
				}
			}
			
			return Sets.newHashSet(iUserRepository.findAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}

}
