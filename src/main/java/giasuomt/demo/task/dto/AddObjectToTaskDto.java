package giasuomt.demo.task.dto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddObjectToTaskDto {
	private String attributeName;
	
	private Long objectId;
	
	private String taskId;
}
