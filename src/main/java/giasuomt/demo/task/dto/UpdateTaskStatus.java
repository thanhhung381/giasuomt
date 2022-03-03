package giasuomt.demo.task.dto;

import lombok.Setter;
import giasuomt.demo.task.util.TaskStatus;
import lombok.Getter;

@Getter
@Setter
public class UpdateTaskStatus {
	private String id;
	
	private TaskStatus status;
}
