package giasuomt.demo.user.dto;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAndDeleteRoleForUser {
	
	private Long id;
	
	private Set<Long> idRole=new HashSet<>();
	
}
