package giasuomt.demo.staff.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.staff.dto.ApplicationResponse;
import giasuomt.demo.staff.dto.TaskListResponse;
import giasuomt.demo.task.model.Application;
import giasuomt.demo.task.model.Task;
import giasuomt.demo.task.repository.IApplicationRepository;
import giasuomt.demo.task.repository.ITaskRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MainStaffService implements IMainStaffService {

	private ITaskRepository iTaskRepository;

	private IApplicationRepository iApplicationRepository;

	private MapDtoToModel mapDtoToModel;

	public List<TaskListResponse> findAllTask() {
		List<Task> task = iTaskRepository.findAll();
		List<TaskListResponse> listResponses = taskMapToTaskRepsonseByList(task);
		return listResponses;
	}

	public List<Tutor> findAllTutorBelongToTask() {
		List<Task> tasks = iTaskRepository.findAll();
		List<Tutor> tutorBelongtoTask = new ArrayList<>();
		for (Task task : tasks) {
			for(Application app : task.getApplications()) {
				tutorBelongtoTask.add(app.getTutor());
			}
		}
		
//		for (int i = 0; i < tasks.size(); i++) {

//			int n = tasks.get(i).getApplications().size();
	//		for (int j = 0; j < n; j++) {

		//		tutorBelongtoTask.add(tasks.get(i).getApplications().get(j).getTutor());
//
//			}

//		}

		return tutorBelongtoTask;
	}

	private void taskMapToTaskResponse(Task task, TaskListResponse listResponse) {
		listResponse = (TaskListResponse) mapDtoToModel.map(task, listResponse);
	//	listResponse.setId(task.getId());
		listResponse.setVersion(task.getVersion());
		listResponse.setApplications((Set<ApplicationResponse>) applicationToMapByList(task.getApplications()));
		listResponse.setJobs(task.getJobs());
//		listResponse.setLearners(task.getLearners());
//		listResponse.setRegisters(task.getRegisters());
	//	listResponse.setRequires(task.getRequires());
	//	listResponse.setSubjects(task.getSubjects());
		listResponse.setTaskPlaceAddresses(task.getTaskPlaceAddresses());
	}

	private List<TaskListResponse> taskMapToTaskRepsonseByList(List<Task> tasks) {
		List<TaskListResponse> listResponses = new ArrayList<>();
		for (Task task : tasks) {
			TaskListResponse listResponse = new TaskListResponse();
			taskMapToTaskResponse(task, listResponse);
			listResponses.add(listResponse);
		}
		return listResponses;
	}

	private void applicationMapToDto(Application application, ApplicationResponse applicationDto) {
		applicationDto.setId(application.getId());
		applicationDto.setIdPerson(application.getTutor().getId());

	}

	private List<ApplicationResponse> applicationToMapByList(Set<Application> set) {
		List<ApplicationResponse> applicationDtos = new ArrayList<>();
		for (Application application : set) {
			ApplicationResponse applicationResponse = new ApplicationResponse();
			applicationMapToDto(application, applicationResponse);
			applicationDtos.add(applicationResponse);
		}
		return applicationDtos;
	}

}
