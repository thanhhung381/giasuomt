package giasuomt.demo.user.dto;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAndDeleteRoleForUser {
	
	private Long id;
	
	private List<Long> idRole=new LinkedList<>();
	
}
