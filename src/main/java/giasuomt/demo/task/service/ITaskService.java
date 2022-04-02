package giasuomt.demo.task.service;
import java.util.List;

import java.util.Optional;
import javax.validation.Valid;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.task.dto.AddObjectToTaskDto;
import giasuomt.demo.task.dto.SaveTaskDto;
import giasuomt.demo.task.dto.ResponseTaskForWebDto;
import giasuomt.demo.task.dto.UpdateFreeTimeDto;
import giasuomt.demo.task.dto.UpdateHourDto;
import giasuomt.demo.task.dto.UpdateLessonDto;
import giasuomt.demo.task.dto.UpdateSalaryDto;
import giasuomt.demo.task.dto.UpdateSubjectDto;
import giasuomt.demo.task.dto.UpdateTaskPlaceAddresseDto;
import giasuomt.demo.task.dto.UpdateTaskSignDto;
import giasuomt.demo.task.dto.UpdateTaskStatusDto;
import giasuomt.demo.task.model.Task;

public interface ITaskService extends IGenericService<SaveTaskDto, Task, String> {

	public Task save(SaveTaskDto dto,Task task);
	
	public  Optional<Task> findByTaskCode(String taskCode);



	public Task addObject(@Valid AddObjectToTaskDto dto);

	public Task deleteObject(@Valid AddObjectToTaskDto dto);
	
	
	public Task updateSubject(UpdateSubjectDto dto);
	

	
	public Task UpdateLesson(UpdateLessonDto dto);
	
	public Task updateHour(UpdateHourDto dto);
	
	public Task updateFreeTime(UpdateFreeTimeDto dto);
	
	public Task updateSalary(UpdateSalaryDto dto);
	
	public Task updateTaskPlaceAddress(UpdateTaskPlaceAddresseDto dto);
	
	public Task updateTaskStatusDto(UpdateTaskStatusDto dto);
	
	List<Task> availableTaskList();
	
	List<Task> unavailableTaskList();
	
	Task updateTaskSignDto(UpdateTaskSignDto dto);
	
	public List<ResponseTaskForWebDto> findAllAvailableTaskListForWeb();


	
}
