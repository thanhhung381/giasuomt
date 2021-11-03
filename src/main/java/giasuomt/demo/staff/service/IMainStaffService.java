package giasuomt.demo.staff.service;

import java.util.List;

import giasuomt.demo.person.model.Person;
import giasuomt.demo.staff.dto.TaskListResponse;

public interface IMainStaffService {
	public List<TaskListResponse> findAllTask();
	
	public List<Person> findAllPersonBelongToTask();
}
