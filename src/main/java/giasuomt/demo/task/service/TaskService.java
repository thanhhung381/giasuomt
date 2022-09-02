package giasuomt.demo.task.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import giasuomt.demo.commondata.generator.TaskCodeGenerator;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.commondata.util.Gender;
import giasuomt.demo.educational.model.SubjectGroup;
import giasuomt.demo.educational.repository.ISubjectGroupRepository;
import giasuomt.demo.location.dto.SaveTaskPlaceAddressDto;
import giasuomt.demo.location.model.TaskPlaceAddress;
import giasuomt.demo.location.repository.IAreaRepository;
import giasuomt.demo.location.repository.ITaskPlaceAddressRepository;
import giasuomt.demo.person.repository.IRegisterAndLearnerRepository;
import giasuomt.demo.task.dto.AddObjectToTaskDto;
import giasuomt.demo.task.dto.ResponseTaskForWebDto;
import giasuomt.demo.task.dto.ResponseTaskPlaceAddressDto;
import giasuomt.demo.task.dto.SaveTaskDto;
import giasuomt.demo.task.dto.UpdateFreeTimeDto;
import giasuomt.demo.task.dto.UpdateLessonDto;
import giasuomt.demo.task.dto.UpdateSalaryDto;
import giasuomt.demo.task.dto.UpdateSubjectDto;
import giasuomt.demo.task.dto.UpdateTaskPlaceAddresseDto;
import giasuomt.demo.task.dto.UpdateTaskSignDto;
import giasuomt.demo.task.dto.UpdateTaskStatusDto;
import giasuomt.demo.task.model.Task;
import giasuomt.demo.task.repository.IApplicationRepository;
import giasuomt.demo.task.repository.ITaskRepository;
import giasuomt.demo.task.util.TaskSign;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskService extends GenericService<SaveTaskDto, Task, String> implements ITaskService {

	private ITaskRepository iTaskRepository;

	private MapDtoToModel mapDtoToModel;

	private IAreaRepository iAreaRepository;

	private ITaskPlaceAddressRepository iTaskPlaceAddressRepository;

	private ISubjectGroupRepository iSubjectGroupRepository;

	private IRegisterAndLearnerRepository iRegisterAndLearnerRepository;

	private IApplicationRepository iApplicationRepository;

	public Task create(SaveTaskDto dto) {
		Task task = new Task();

		task = (Task) mapDtoToModel.map(dto, task);

		String responsCharactor = generateTaskCode();

		task.setId(TaskCodeGenerator.generatorCode().concat(responsCharactor));

		return save(dto, task);
	}

	public Task update(SaveTaskDto dto) {
		Task task = iTaskRepository.findByIdString(dto.getId());

		task = (Task) mapDtoToModel.map(dto, task);

		return save(dto, task);
	}

	private void mapDto(Task task, SaveTaskDto dto) {

		


	
		
		// String taskCode = dto.getId();

		// Subject vì sau khi nhập subject thì đã có tồn tại subjectgroup rồi
//		List<Long> subjectIds = dto.getIdSubjects();
		// List<Subject> subjects = new ArrayList<>();
		// for (int i = 0; i < subjectIds.size(); i++) {
//			Subject subject = iSubjectRepository.getOne(subjectIds.get(i));
		// subjects.add(subject);
//
		// }
		//
		// task.setSubjects(subjects);

		Set<String> idSubjectGroups = dto.getIdSubjectGroup();
		Set<SubjectGroup> subjectGroups = new HashSet<>();

		for (String id : idSubjectGroups) {
			SubjectGroup subjectGroup = iSubjectGroupRepository.getOne(id);
			subjectGroups.add(subjectGroup);
		}
		task.setSubjectGroups(subjectGroups);

		Set<Gender> genders = new HashSet<>();
		for (Gender gender : dto.getGenderRequired()) {

			genders.add(gender);
		}
		task.setGenderRequired(genders);
		
		Set<TaskSign> taskSigns = new HashSet<>();
		for (TaskSign taskSign : dto.getTaskSigns()) {

			taskSigns.add(taskSign);
		}
		task.setTaskSign(taskSigns);



		Set<SaveTaskPlaceAddressDto> saveTaskPlaceAddressDtos = dto.getSaveTaskPlaceAddressDtos();
		for (TaskPlaceAddress taskPlaceAddress : task.getTaskPlaceAddresses()) {
			boolean deleteThis = true;
			for (SaveTaskPlaceAddressDto saveTaskPlaceAddressDto : saveTaskPlaceAddressDtos) {
				if (taskPlaceAddress.getId() == saveTaskPlaceAddressDto.getId()) {
					deleteThis = false;
				}
			}
			if (deleteThis) {
				task.removeTaskPlaceAddress(taskPlaceAddress);

			}
		}
		for (SaveTaskPlaceAddressDto saveTaskPlaceAddressDto : saveTaskPlaceAddressDtos) {
			SaveTaskPlaceAddressDto placeAddressDto = saveTaskPlaceAddressDto;
			if (placeAddressDto.getId() != null && placeAddressDto.getId() > 0) {
				TaskPlaceAddress taskPlaceAddress = iTaskPlaceAddressRepository.getOne(placeAddressDto.getId());

				taskPlaceAddress = (TaskPlaceAddress) mapDtoToModel.map(placeAddressDto, taskPlaceAddress);

				if (iAreaRepository.findById(placeAddressDto.getIdArea()).isPresent())
					taskPlaceAddress.setArea(iAreaRepository.getOne(placeAddressDto.getIdArea()));
			
				task.addTaskPlaceAddress(taskPlaceAddress);
			} else {
				TaskPlaceAddress taskPlaceAddress = new TaskPlaceAddress();
				taskPlaceAddress = (TaskPlaceAddress) mapDtoToModel.map(placeAddressDto, taskPlaceAddress);

				if (iAreaRepository.findById(placeAddressDto.getIdArea()).isPresent())
					taskPlaceAddress.setArea(iAreaRepository.getOne(placeAddressDto.getIdArea()));
				
				task.addTaskPlaceAddress(taskPlaceAddress);
			}
		}
	}

	public Task save(SaveTaskDto dto, Task task) {

		try {

			mapDto(task, dto);

			return iTaskRepository.save(task);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private String generateTaskCode() {

		Task listTask = iTaskRepository.findByTaskLast();

		// lấy stt task id trước đó
		int count = 0;

		if (listTask != null) {
			String dayEnd = listTask.getId(); // listTask.get(n-1).getId();// Lấy mã cuối ngày
												// so sánh

			if (dayEnd != null) {
				count = TaskCodeGenerator.generateResponsiveReserve(dayEnd.substring(4, 6));

				if (TaskCodeGenerator.AutoGennerate(dayEnd) == -1 || TaskCodeGenerator.AutoGennerate(dayEnd) == 2)// check
																													// //
																													// ko
				{
					count = 1;

				} else if (TaskCodeGenerator.AutoGennerate(dayEnd) == 3) {
					count += 1;

				}

			} else {
				count = 1;
			}
		} else {
			count = 1;
		}

		String responsCharacter = TaskCodeGenerator.generateResponsive(count);
		return responsCharacter;

	}

	@Override
	public Set<Task> createAll(Set<SaveTaskDto> dtos) {
		try {
			Set<Task> tasks = new HashSet<>();
			for (SaveTaskDto dto : dtos) {
				Task task = new Task();
				
				task = (Task) mapDtoToModel.map(dto, task);
				
				task.setId(dto.getId());

				Set<String> idSubjectGroups = dto.getIdSubjectGroup();
				Set<SubjectGroup> subjectGroups = new HashSet<>();

				for (String id : idSubjectGroups) { 
					SubjectGroup subjectGroup = iSubjectGroupRepository.getOne(id);
					subjectGroups.add(subjectGroup);
				}
				task.setSubjectGroups(subjectGroups);

				Set<Gender> genders = new HashSet<>();
				for (Gender gender : dto.getGenderRequired()) {

					genders.add(gender);
				}
				task.setGenderRequired(genders);
				
				Set<TaskSign> taskSigns = new HashSet<>();
				for (TaskSign taskSign : dto.getTaskSigns()) {

					taskSigns.add(taskSign);
				}
				task.setTaskSign(taskSigns);



				Set<SaveTaskPlaceAddressDto> saveTaskPlaceAddressDtos = dto.getSaveTaskPlaceAddressDtos();
				for (TaskPlaceAddress taskPlaceAddress : task.getTaskPlaceAddresses()) {
					boolean deleteThis = true;
					for (SaveTaskPlaceAddressDto saveTaskPlaceAddressDto : saveTaskPlaceAddressDtos) {
						if (taskPlaceAddress.getId() == saveTaskPlaceAddressDto.getId()) {
							deleteThis = false;
						}
					}
					if (deleteThis) {
						task.removeTaskPlaceAddress(taskPlaceAddress);

					}
				}
				for (SaveTaskPlaceAddressDto saveTaskPlaceAddressDto : saveTaskPlaceAddressDtos) {
					SaveTaskPlaceAddressDto placeAddressDto = saveTaskPlaceAddressDto;
					if (placeAddressDto.getId() != null && placeAddressDto.getId() > 0) {
						TaskPlaceAddress taskPlaceAddress = iTaskPlaceAddressRepository.getOne(placeAddressDto.getId());

						taskPlaceAddress = (TaskPlaceAddress) mapDtoToModel.map(placeAddressDto, taskPlaceAddress);

						if (iAreaRepository.findById(placeAddressDto.getIdArea()).isPresent())
							taskPlaceAddress.setArea(iAreaRepository.getOne(placeAddressDto.getIdArea()));
						
					
						task.addTaskPlaceAddress(taskPlaceAddress);
					} else {
						TaskPlaceAddress taskPlaceAddress = new TaskPlaceAddress();
						taskPlaceAddress = (TaskPlaceAddress) mapDtoToModel.map(placeAddressDto, taskPlaceAddress);

						if (iAreaRepository.findById(placeAddressDto.getIdArea()).isPresent())
							taskPlaceAddress.setArea(iAreaRepository.getOne(placeAddressDto.getIdArea()));
						
						task.addTaskPlaceAddress(taskPlaceAddress);
					}
				}

				tasks.add(task);
			}

			return Sets.newHashSet(iTaskRepository.saveAll(tasks));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Optional<Task> findByTaskCode(String taskCode) {
		return null;
	}

	// Add Object to Task
	@Override
	public Task addObject(AddObjectToTaskDto dto) {
		/*
		 * try { Task task = iTaskRepository.getOne(dto.getTaskId());
		 * 
		 * switch (dto.getAttributeName()) { case "subjects": Subject subject =
		 * iSubjectRepository.getOne(dto.getObjectId()); if
		 * (!task.getSubjects().contains(subject)) { task.addSubject(subject);
		 * task.setSubjectApperance(TaskAppearanceGenerator.generateSubjectAppearance(
		 * task.getSubjects())); } ; break; default: break; } ;
		 * 
		 * return iTaskRepository.save(task); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		return null;
	}

	// Delete Object from Task
	@Override
	public Task deleteObject(AddObjectToTaskDto dto) {
		/*
		 * try { Task task = iTaskRepository.getOne(dto.getTaskId());
		 * 
		 * switch (dto.getAttributeName()) { case "subjects": Subject subject =
		 * iSubjectRepository.getOne(dto.getObjectId()); if
		 * (task.getSubjects().contains(subject)) { task.removeSubject(subject); } ;
		 * break; default: break; } ;
		 * 
		 * return iTaskRepository.save(task); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		return null;
	}

	@Override
	public Task updateSubject(UpdateSubjectDto dto) {

		try {
			Task task = iTaskRepository.getOne(dto.getId());

			/*
			 * List<Long> subjectIds = dto.getSubjectIds(); List<Subject> subjects = new
			 * ArrayList<>(); for (int i = 0; i < subjectIds.size(); i++) { Subject subject
			 * = iSubjectRepository.getOne(subjectIds.get(i)); subjects.add(subject);
			 * 
			 * } task.setSubjects(subjects);
			 */

			task.setSubjectNote(dto.getSubjectNote());

			return iTaskRepository.save(task);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Task UpdateLesson(UpdateLessonDto dto) {

		try {
			Task task = iTaskRepository.getOne(dto.getId());

			task.setLessonPeriodOfTime(dto.getLessonPeriodOfTime());
			task.setLessonNumber(dto.getLessonNumber());

			return iTaskRepository.save(task);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Task updateFreeTime(UpdateFreeTimeDto dto) {
		try {
			Task task = iTaskRepository.getOne(dto.getId());

			task.setFreeTime(dto.getFreeTime());
			return iTaskRepository.save(task);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Task updateSalary(UpdateSalaryDto dto) {
		try {
			Task task = iTaskRepository.getOne(dto.getId());

			task.setSalaryForStudent(dto.getSalaryForStudent());
			
			task.setSalaryForGraduatedStudent(dto.getSalaryForGraduatedStudent());
			
			task.setSalaryForStudent(dto.getSalaryForStudent());

			task.setSalaryPerTime(dto.getSalaryPerTime());

			task.setUnitOfSalary(dto.getUnitOfSalary());

			return iTaskRepository.save(task);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Task updateTaskPlaceAddress(UpdateTaskPlaceAddresseDto dto) {

		try {
			Task task = iTaskRepository.getOne(dto.getId());

			Set<SaveTaskPlaceAddressDto> saveTaskPlaceAddressDtos = dto.getPlaceAddressDtos();
			for (TaskPlaceAddress taskPlaceAddress : task.getTaskPlaceAddresses()) {
				boolean deleteThis = true;
				for (SaveTaskPlaceAddressDto saveTaskPlaceAddressDto : saveTaskPlaceAddressDtos) {
					if (taskPlaceAddress.getId() == saveTaskPlaceAddressDto.getId()) {
						deleteThis = false;
					}
				}
				if (deleteThis) {
					task.removeTaskPlaceAddress(taskPlaceAddress);

				}
			}
			for (SaveTaskPlaceAddressDto saveTaskPlaceAddressDto : saveTaskPlaceAddressDtos) {
				SaveTaskPlaceAddressDto placeAddressDto = saveTaskPlaceAddressDto;
				if (placeAddressDto.getId() != null && placeAddressDto.getId() > 0) {
					TaskPlaceAddress taskPlaceAddress = iTaskPlaceAddressRepository.getOne(placeAddressDto.getId());
					taskPlaceAddress = (TaskPlaceAddress) mapDtoToModel.map(placeAddressDto, taskPlaceAddress);
					taskPlaceAddress.setArea(iAreaRepository.getOne(placeAddressDto.getIdArea()));
					task.addTaskPlaceAddress(taskPlaceAddress);
				} else {
					TaskPlaceAddress taskPlaceAddress = new TaskPlaceAddress();
					taskPlaceAddress = (TaskPlaceAddress) mapDtoToModel.map(placeAddressDto, taskPlaceAddress);
					taskPlaceAddress.setArea(iAreaRepository.getOne(placeAddressDto.getIdArea()));
					task.addTaskPlaceAddress(taskPlaceAddress);
				}
			}

			return iTaskRepository.save(task);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;

	}

	@Override
	public Set<Task> availableTaskList() {

		return iTaskRepository.findByAvailableTaskList();
	}

	@Override
	public Set<Task> unavailableTaskList() {

		return iTaskRepository.findByUnavailableTaskList();
	}

	@Override
	public Task updateTaskStatusDto(UpdateTaskStatusDto dto) {

		try {
			Task task = iTaskRepository.getOne(dto.getId());

			task.setStatus(dto.getStatus());

			return iTaskRepository.save(task);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Task updateTaskSignDto(UpdateTaskSignDto dto) {
		try {

			Task task = iTaskRepository.getOne(dto.getId());

			Set<TaskSign> taskSignList = new HashSet<>();

			for (int i = 0; i < dto.getTaskSigns().size(); i++) {
				taskSignList.add(dto.getTaskSigns().get(i));
			}

			task.setTaskSign(taskSignList);
			return iTaskRepository.save(task);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void mapTasktoTaskForWeb(ResponseTaskForWebDto dto, Task task) {
		dto.setId(task.getId());
		dto.setSubjectNote(task.getSubjectNote());
		dto.setTaskPlaceAddresses(findAllTaskPlaceAddress(task.getTaskPlaceAddresses()));
		dto.setTaskPlaceType(task.getTaskPlaceType());
		dto.setLessonNumber(task.getLessonNumber());
		dto.setLessonPeriodOfTime(task.getLessonPeriodOfTime());
		dto.setFreeTime(task.getFreeTime());
		
		dto.setSalaryForGraduatedStudent(task.getSalaryForGraduatedStudent());
		
		dto.setSalaryForStudent(task.getSalaryForStudent());
		
		dto.setSalaryForTeacher(task.getSalaryForTeacher());
		
		dto.setUnitOfSalary(task.getUnitOfSalary());
		dto.setSalaryPerTime(task.getSalaryPerTime());
		dto.setLearnerNumber(task.getLearnerNumber());
		dto.setLearnerNote(task.getLearnerNote());
		dto.setGenderRequired(task.getGenderRequired());
		dto.setHienDangLaRequired(task.getHienDangLaRequired());
		dto.setRequireNote(task.getRequireNote());
		dto.setSubjectGroups(task.getSubjectGroups());
		dto.setVoiceRequired(task.getVoiceRequired());
	
	}

	private Set<ResponseTaskForWebDto> mapTasktoTaskForWebList(Set<Task> tasks) {
		Set<ResponseTaskForWebDto> responseTaskForWebDtos = new HashSet<>();
		for (Task task : tasks) {
			ResponseTaskForWebDto responseTaskForWebDto = new ResponseTaskForWebDto();
			mapTasktoTaskForWeb(responseTaskForWebDto, task);
			responseTaskForWebDtos.add(responseTaskForWebDto);
		}
		return responseTaskForWebDtos;
	}

	@Override
	public Set<ResponseTaskForWebDto> findAllAvailableTaskListForWeb() {
		return mapTasktoTaskForWebList(iTaskRepository.findByAvailableTaskList());
	}

	private void mapTaskAdderessToTaskAddressDto(ResponseTaskPlaceAddressDto dto, TaskPlaceAddress taskPlaceAddress) {
		dto.setRelAddNumber(taskPlaceAddress.getRelAddNumber());
		dto.setArea(taskPlaceAddress.getArea());
		dto.setAddStreet(taskPlaceAddress.getAddStreet());
		dto.setAddStreetNote(taskPlaceAddress.getAddStreetNote());
	}

	private Set<ResponseTaskPlaceAddressDto> mapTaskAdderessToTaskAddressDtoList(
			Set<TaskPlaceAddress> taskPlaceAddresses) {
		Set<ResponseTaskPlaceAddressDto> addressDtos = new HashSet<>();
		for (TaskPlaceAddress taskPlaceAddress : taskPlaceAddresses) {
			ResponseTaskPlaceAddressDto addressDto = new ResponseTaskPlaceAddressDto();
			mapTaskAdderessToTaskAddressDto(addressDto, taskPlaceAddress);
			addressDtos.add(addressDto);
		}
		return addressDtos;
	}

	private Set<ResponseTaskPlaceAddressDto> findAllTaskPlaceAddress(Set<TaskPlaceAddress> addresses) {
		return mapTaskAdderessToTaskAddressDtoList(addresses);
	}

}
