package giasuomt.demo.role.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.role.dto.SaveRoleDto;
import giasuomt.demo.role.model.Role;
import giasuomt.demo.role.repository.IRoleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleService extends GenericService<SaveRoleDto, Role, Long> implements IRoleService {
	
	
	
	private IRoleRepository iRoleRepository;
	
	private MapDtoToModel mapDtoToModel;
	
	
	public Role create(SaveRoleDto dto) {
		
		Role role=new Role();
		
		return save(dto,role);
	}

	public Role save(SaveRoleDto dto,Role role)
	{
		try {
			
			mapDto(dto, role);
			
			return iRoleRepository.save(role);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void mapDto(SaveRoleDto dto,Role role)
	{
		role=(Role)mapDtoToModel.map(dto, role);
	}
	

	@Override
	public Role update(SaveRoleDto dto) {
		
		Role role=iRoleRepository.getOne(dto.getId());
		
		return save(dto,role);
	}

}
