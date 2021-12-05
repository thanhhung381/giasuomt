package giasuomt.demo.task.service;
import java.util.Optional;
import javax.validation.Valid;
import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.task.dto.AddObjectToTaskDto;
import giasuomt.demo.task.dto.SaveTaskDto;
import giasuomt.demo.task.model.Task;

public interface ITaskService extends IGenericService<SaveTaskDto, Task, String> {

	public Task save(SaveTaskDto dto,Task task);
	
	public  Optional<Task> findByTaskCode(String taskCode);



	public Task addObject(@Valid AddObjectToTaskDto dto);

	public Task deleteObject(@Valid AddObjectToTaskDto dto);


	
}
