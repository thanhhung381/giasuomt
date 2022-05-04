package giasuomt.demo.task.dto;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSubjectGroupMaybeDto {

	private Long id;
	

	private Set<String> idSubjectGroupMaybes=new HashSet<>();
}
