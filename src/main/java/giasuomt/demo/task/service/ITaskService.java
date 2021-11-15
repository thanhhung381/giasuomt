package giasuomt.demo.task.service;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.task.dto.SaveTaskDto;
import giasuomt.demo.task.model.Task;

public interface ITaskService extends IGenericService<SaveTaskDto, Task, Long> {
	public Task save(SaveTaskDto dto,Task task);
	
	public Task findByTaskCode(String taskCode);
	
	public boolean checkTaskCodeExist(String taskcode);
	
}
