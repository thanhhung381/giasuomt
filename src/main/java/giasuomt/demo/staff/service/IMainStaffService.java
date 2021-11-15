package giasuomt.demo.staff.service;
import java.util.List;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.staff.dto.TaskListResponse;

public interface IMainStaffService {
	public List<TaskListResponse> findAllTask();
	
	public List<Tutor> findAllTutorBelongToTask();
}
