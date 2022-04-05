package giasuomt.demo.task.service;

import java.lang.reflect.Method;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;

import com.amazonaws.services.athena.AbstractAmazonAthena;


import giasuomt.demo.commondata.generator.TaskCodeGenerator;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.commondata.util.DateTimeUtils;
import giasuomt.demo.commondata.util.Gender;
import giasuomt.demo.commondata.util.HienDangLa;
import giasuomt.demo.commondata.util.Voice;
import giasuomt.demo.educational.model.SubjectGroup;
import giasuomt.demo.educational.repository.ISubjectGroupRepository;
import giasuomt.demo.location.model.SaveTaskPlaceAddressDto;
import giasuomt.demo.location.model.TaskPlaceAddress;
import giasuomt.demo.location.repository.IAreaRepository;
import giasuomt.demo.location.repository.ITaskPlaceAddressRepository;
import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.person.repository.IRegisterAndLearnerRepository;
import giasuomt.demo.task.dto.AddObjectToTaskDto;
import giasuomt.demo.task.dto.ResponseTaskPlaceAddressDto;
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
import giasuomt.demo.task.repository.IApplicationRepository;

import giasuomt.demo.task.repository.ITaskRepository;
import giasuomt.demo.task.util.TaskAppearanceGenerator;
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

		System.out.println(responsCharactor);

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

		List<Long> idSubjectGroups = dto.getIdSubjectGroup();
		List<SubjectGroup> subjectGroups = new LinkedList<>();

		for (Long id : idSubjectGroups) {
			SubjectGroup subjectGroup = iSubjectGroupRepository.getOne(id);
			subjectGroups.add(subjectGroup);
		}
		task.setSubjectGroups(subjectGroups);
		
		
		List<Gender> genders=new LinkedList<>();
		for (Gender gender : dto.getGenderRequired()) {
			
			genders.add(gender);
		}
		task.setGenderRequired(genders);
		
		List<Voice> voices=new LinkedList<>();
		for (Voice voice : dto.getVoiceRequired()) {
			voices.add(voice);
		}
		task.setVoiceRequired(voices);

		List<HienDangLa> hienDangLas=new LinkedList<>();
		for (HienDangLa hienDangLa : dto.getHienDangLaRequired()) {
			hienDangLas.add(hienDangLa);
		}
		task.setHienDangLaRequired(hienDangLas);


		List<SaveTaskPlaceAddressDto> saveTaskPlaceAddressDtos = dto.getSaveTaskPlaceAddressDtos();
		for (int i = 0; i < task.getTaskPlaceAddresses().size(); i++) {
			boolean deleteThis = true;
			for (int j = 0; j < saveTaskPlaceAddressDtos.size(); j++) {
				if (task.getTaskPlaceAddresses().get(i).getId() == saveTaskPlaceAddressDtos.get(j).getId()) {
					deleteThis = false;
				}
			}
			if (deleteThis) {
				task.removeTaskPlaceAddress(task.getTaskPlaceAddresses().get(i));
				i--;
			}
		}
		for (int i = 0; i < saveTaskPlaceAddressDtos.size(); i++) {
			SaveTaskPlaceAddressDto placeAddressDto = saveTaskPlaceAddressDtos.get(i);
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
	public List<Task> createAll(List<SaveTaskDto> dtos) {
		try {
			List<Task> tasks = new LinkedList<>();
			for (SaveTaskDto dto : dtos) {
				Task task = new Task();
				// String responsCharactor = generateTaskCode();

//				task.setId(TaskCodeGenerator.generatorCode().concat(responsCharactor));

				// mã có sẵn
				mapDto(task, dto);

				tasks.add(task);
			}

			return iTaskRepository.saveAll(tasks);

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

			task.setSubjectApperance(dto.getSubjectApperance());

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

			task.setLessonNumber(dto.getLessonNumber());
			task.setLessonNumberPerTime(dto.getLessonNumberPerTime());

			return iTaskRepository.save(task);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Task updateHour(UpdateHourDto dto) {

		try {
			Task task = iTaskRepository.getOne(dto.getId());

			task.setHour(dto.getHour());
			task.setHourPerTime(dto.getLessonNumberPerTime());

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

			task.setSalary(dto.getSalary());

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

			List<SaveTaskPlaceAddressDto> saveTaskPlaceAddressDtos = dto.getPlaceAddressDtos();
			for (int i = 0; i < task.getTaskPlaceAddresses().size(); i++) {
				boolean deleteThis = true;
				for (int j = 0; j < saveTaskPlaceAddressDtos.size(); j++) {
					if (task.getTaskPlaceAddresses().get(i).getId() == saveTaskPlaceAddressDtos.get(j).getId()) {
						deleteThis = false;
					}
				}
				if (deleteThis) {
					task.removeTaskPlaceAddress(task.getTaskPlaceAddresses().get(i));
					i--;
				}
			}
			for (int i = 0; i < saveTaskPlaceAddressDtos.size(); i++) {
				SaveTaskPlaceAddressDto placeAddressDto = saveTaskPlaceAddressDtos.get(i);
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
	public List<Task> availableTaskList() {


		return iTaskRepository.findByAvailableTaskList();
	}

	@Override
	public List<Task> unavailableTaskList() {

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

			List<TaskSign> taskSignList = new LinkedList<>();

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
		dto.setRequireApperance(task.getRequireApperance());
		dto.setRequireNote(task.getRequireNote());
		dto.setSubjectApperance(task.getSubjectApperance());
		dto.setSubjectNote(task.getSubjectNote());
		dto.setTaskPlaceAddresses(findAllTaskPlaceAddress(task.getTaskPlaceAddresses()));
		dto.setTaskPlaceType(task.getTaskPlaceType());
		dto.setLessonNumber(task.getLessonNumber());
		dto.setLessonNumberPerTime(task.getLessonNumberPerTime());
		dto.setHour(task.getHour());
		dto.setHourPerTime(task.getHourPerTime());
		dto.setFreeTime(task.getFreeTime());
		dto.setSalary(task.getSalary());
		dto.setUnitOfSalary(task.getUnitOfSalary());
		dto.setSalaryPerTime(task.getSalaryPerTime());
		dto.setLearnerNumber(task.getLearnerNumber());
		dto.setTaskNote(task.getTaskNote());

	}

	private List<ResponseTaskForWebDto> mapTasktoTaskForWebList(List<Task> tasks) {
		List<ResponseTaskForWebDto> responseTaskForWebDtos = new LinkedList<>();
		for (Task task : tasks) {
			ResponseTaskForWebDto responseTaskForWebDto = new ResponseTaskForWebDto();
			mapTasktoTaskForWeb(responseTaskForWebDto, task);
			responseTaskForWebDtos.add(responseTaskForWebDto);
		}
		return responseTaskForWebDtos;
	}

	@Override
	public List<ResponseTaskForWebDto> findAllAvailableTaskListForWeb() {

		return mapTasktoTaskForWebList(iTaskRepository.findByAvailableTaskList());
	}

	private void mapTaskAdderessToTaskAddressDto(ResponseTaskPlaceAddressDto dto, TaskPlaceAddress taskPlaceAddress) {
		dto.setArea(taskPlaceAddress.getArea());
		dto.setRelAddNote(taskPlaceAddress.getRelAddNote());
		dto.setRelAddNumber(taskPlaceAddress.getRelAddNumber());
		dto.setRelAddStreet(taskPlaceAddress.getRelAddStreet());
	}

	private List<ResponseTaskPlaceAddressDto> mapTaskAdderessToTaskAddressDtoList(
			List<TaskPlaceAddress> taskPlaceAddresses) {
		List<ResponseTaskPlaceAddressDto> addressDtos = new LinkedList<>();

		for (TaskPlaceAddress taskPlaceAddress : taskPlaceAddresses) {
			ResponseTaskPlaceAddressDto addressDto = new ResponseTaskPlaceAddressDto();
			mapTaskAdderessToTaskAddressDto(addressDto, taskPlaceAddress);
			addressDtos.add(addressDto);

		}

		return addressDtos;
	}

	private List<ResponseTaskPlaceAddressDto> findAllTaskPlaceAddress(List<TaskPlaceAddress> addresses) {
		return mapTaskAdderessToTaskAddressDtoList(addresses);
	}

}
