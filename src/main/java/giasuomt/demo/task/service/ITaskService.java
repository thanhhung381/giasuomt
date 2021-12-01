package giasuomt.demo.task.service;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.task.dto.AddSubjectToTaskDto;
import giasuomt.demo.task.dto.SaveTaskDto;
import giasuomt.demo.task.model.Task;

public interface ITaskService extends IGenericService<SaveTaskDto, Task, String> {
	public Task save(SaveTaskDto dto,Task task);
	
	public  Optional<Task> findByTaskCode(String taskCode);

	public Task addSubjectToTask(@Valid AddSubjectToTaskDto dto);

	public Task deleteSubjectFromTask(@Valid AddSubjectToTaskDto dto);

//	public List<RegisterAndLearner> findRegistersById(Long id);
//
//	public List<RegisterAndLearner> findLearnersById(Long id);

	
}
