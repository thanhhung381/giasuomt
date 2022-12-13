package giasuomt.demo.task.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import giasuomt.demo.comment.repository.IApplicationCommentRepository;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.educational.model.SubjectGroup;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.person.repository.ITutorRepository;
import giasuomt.demo.task.dto.SaveApplicationDto;
import giasuomt.demo.task.dto.SaveTutorCreateDto;
import giasuomt.demo.task.dto.UpdateApplicationSignDto;
import giasuomt.demo.task.model.Application;
import giasuomt.demo.task.model.Task;
import giasuomt.demo.task.repository.IApplicationRepository;
import giasuomt.demo.task.repository.ITaskRepository;
import giasuomt.demo.task.util.ApplicationSign;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class ApplicationService extends GenericService<SaveApplicationDto, Application, String>
		implements IApplicationService {

	private MapDtoToModel mapDtoToModel;

	private ITaskRepository iTaskRepository;

	private IApplicationRepository iApplicationRepository;

	private ITutorRepository iTutorRepository;

	private IApplicationCommentRepository iApplicationCommentRepository;

	public Application create(SaveApplicationDto dto) {
		if (checkExistApplicationOfTutorForThisTask(dto.getTutorId(), dto.getTaskId())) {
			Application application = new Application();
			application = (Application) mapDtoToModel.map(dto, application);
			Task task = iTaskRepository.getOne(dto.getTaskId());
			Set<SubjectGroup> subjectGroupOfTask = task.getSubjectGroups();
			Tutor tutor = iTutorRepository.getOne(dto.getTutorId());
			Set<SubjectGroup> subjectGroupOfTutor = tutor.getSubjectGroupMaybes();
			if (!subjectGroupOfTutor.isEmpty() && !subjectGroupOfTask.isEmpty()) {
				subjectGroupOfTask.stream()
						.filter(subjectGtask -> subjectGroupOfTutor.stream()
								.anyMatch(subjectGTutor -> !subjectGTutor.getId().equals(subjectGtask.getId())))
						.forEach(taskSubjectG -> subjectGroupOfTutor.add(taskSubjectG));
				tutor.setSubjectGroupMaybes(subjectGroupOfTutor);
				tutor = iTutorRepository.save(tutor);
			} else if (subjectGroupOfTutor.isEmpty() && !subjectGroupOfTask.isEmpty()) {
				subjectGroupOfTutor.addAll(subjectGroupOfTask);
				tutor.setSubjectGroupMaybes(subjectGroupOfTutor);
				tutor = iTutorRepository.save(tutor);
			}
			application.setTask(task);
			application.setTutor(tutor);
			application.setId(task.getId().concat("-").concat(String.valueOf(tutor.getId()).concat("-application")));
			return save(dto, application);
		}
		throw new NullPointerException("We can not create because Tutor had existed application for this task ");
	}

	@Override
	public Application update(SaveApplicationDto dto) {
		Application application = iApplicationRepository.getOne(dto.getId());
		application = (Application) mapDtoToModel.map(dto, application);
		application.setTask(iTaskRepository.getOne(dto.getTaskId()));
		application.setTutor(iTutorRepository.getOne(dto.getTutorId()));
		return save(dto, application);
	}

	@Override
	public Set<Application> createAll(Set<SaveApplicationDto> dtos) {
		try {
			Set<Application> applications = new HashSet<>();
			for (SaveApplicationDto dto : dtos) {
				Application application = new Application();
				application = (Application) mapDtoToModel.map(dto, application);
				application.setTask(iTaskRepository.getOne(dto.getTaskId()));
				application.setTutor(iTutorRepository.getOne(dto.getTutorId()));
				applications.add(application);
			}
			return Sets.newHashSet(iApplicationRepository.saveAll(applications));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteByIdAppliction(String id) {
		iApplicationCommentRepository.deleteAll(iApplicationRepository.getOne(id).getComments());// xóa application là
																									// // xóa hết
		iApplicationRepository.deleteById(id);
	}

	@Override
	public Application updateApplicationSign(UpdateApplicationSignDto dto) {
		try {
			Application application = iApplicationRepository.getOne(dto.getId());
			Set<ApplicationSign> applications = new HashSet<>();
			for (ApplicationSign applicationSign : dto.getApplicationSigns()) {
				applications.add(applicationSign);
			}
			application.setApplicationSigns(applications);
			return iApplicationRepository.save(application);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Application tutorCreate(SaveTutorCreateDto dto) {
		Application application = new Application();
		application = (Application) mapDtoToModel.map(dto, application);
		application.setTask(iTaskRepository.getOne(dto.getIdTask()));
		application = iApplicationRepository.save(application);
		// lấy createBy lên và kiếm tra
		if (!checkExistApplicationForTutorCreate(application.getCreatedBy(), dto.getIdTask())) {
			throw new NullPointerException("We can not create because Tutor had existed application for this task ");
		}
		application.setTutor(iTutorRepository.findByCreatedBy(application.getCreatedBy()));
		return iApplicationRepository.save(application);
	}

	private boolean checkExistApplicationOfTutorForThisTask(Long idTutor, String idTask) {
		List<Application> applications = iTutorRepository.getOne(idTutor).getApplications();
		for (Application application : applications) {
			Task task = iTaskRepository.getOne(idTask);
			if (application.getTask() == task)
				return false; // trùng
		}
		return true;
	}

	private boolean checkExistApplicationForTutorCreate(String username, String idTask) {
		List<Application> applications = iTutorRepository.findByCreatedBy(username).getApplications();
		for (Application application : applications) {
			Task task = iTaskRepository.getOne(idTask);
			if (application.getTask() == task)
				return false; // trùng
		}
		return true;
	}

}
