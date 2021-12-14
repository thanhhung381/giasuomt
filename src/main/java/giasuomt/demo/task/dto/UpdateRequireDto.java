package giasuomt.demo.task.dto;

import java.util.LinkedList;
import java.util.List;

import giasuomt.demo.task.model.Require;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRequireDto {
	
	
	private String id;
	
	private List<Long> requireIds = new LinkedList<>();
	
	private String requireApperance;

	private String requireNote;
}
