package giasuomt.demo.task.dto;

import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

import giasuomt.demo.task.util.TaskSign;
import lombok.Getter;

@Getter
@Setter
public class UpdateTaskSignDto {
	
	private String id;
	
	
	private List<TaskSign> taskSigns=new LinkedList<>();
}
