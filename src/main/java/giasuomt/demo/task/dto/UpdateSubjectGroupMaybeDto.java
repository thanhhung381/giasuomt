package giasuomt.demo.task.dto;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSubjectGroupMaybeDto {

	private Long id;
	

	private List<String> idSubjectGroupMaybes=new LinkedList<>();
}
