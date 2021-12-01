package giasuomt.demo.task.dto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddSubjectToTaskDto {
	private Long subjectId;
	
	private String taskId;
}
