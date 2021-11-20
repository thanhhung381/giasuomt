package giasuomt.demo.task.service;
import java.util.List;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.task.dto.SaveTaskDto;
import giasuomt.demo.task.model.Task;

public interface ITaskService extends IGenericService<SaveTaskDto, Task, Long> {
	public Task save(SaveTaskDto dto,Task task);
	
	public Task findByTaskCode(String taskCode);

//	public List<RegisterAndLearner> findRegistersById(Long id);
//
//	public List<RegisterAndLearner> findLearnersById(Long id);

	
}
