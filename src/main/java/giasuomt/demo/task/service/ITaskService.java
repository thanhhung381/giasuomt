package giasuomt.demo.task.service;
import java.util.List;
import java.util.Optional;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.task.dto.SaveTaskDto;
import giasuomt.demo.task.model.Task;

public interface ITaskService extends IGenericService<SaveTaskDto, Task, String> {
	public Task save(SaveTaskDto dto,Task task);
	
	public  Optional<Task> findByTaskCode(String taskCode);

//	public List<RegisterAndLearner> findRegistersById(Long id);
//
//	public List<RegisterAndLearner> findLearnersById(Long id);

	
}
