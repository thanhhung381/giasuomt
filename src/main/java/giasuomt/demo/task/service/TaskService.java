package giasuomt.demo.task.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import giasuomt.demo.commondata.generator.TaskCodeGenerator;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.educational.model.Subject;
import giasuomt.demo.educational.repository.ISubjectRepository;
import giasuomt.demo.location.model.SaveTaskPlaceAddressDto;
import giasuomt.demo.location.model.TaskPlaceAddress;
import giasuomt.demo.location.repository.IAreaRepository;
import giasuomt.demo.location.repository.ITaskPlaceAddressRepository;
import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.person.repository.IRegisterAndLearnerRepository;
import giasuomt.demo.task.dto.AddObjectToTaskDto;
import giasuomt.demo.task.dto.SaveTaskDto;
import giasuomt.demo.task.dto.UpdateFreeTimeDto;
import giasuomt.demo.task.dto.UpdateHourDto;
import giasuomt.demo.task.dto.UpdateLessonDto;
import giasuomt.demo.task.dto.UpdateRequireDto;
import giasuomt.demo.task.dto.UpdateSalaryDto;
import giasuomt.demo.task.dto.UpdateSubjectDto;
import giasuomt.demo.task.dto.UpdateTaskPlaceAddresseDto;
import giasuomt.demo.task.model.Require;
import giasuomt.demo.task.model.Task;
import giasuomt.demo.task.repository.IApplicationRepository;
import giasuomt.demo.task.repository.IRequireRepository;
import giasuomt.demo.task.repository.ITaskRepository;
import giasuomt.demo.task.util.TaskAppearanceGenerator;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskService extends GenericService<SaveTaskDto, Task, String> implements ITaskService {

	private ITaskRepository iTaskRepository;

	private MapDtoToModel mapDtoToModel;

	private IAreaRepository iAreaRepository;

	private ITaskPlaceAddressRepository iTaskPlaceAddressRepository;

	private ISubjectRepository iSubjectRepository;

	private IRequireRepository iRequireRepository;

	private IRegisterAndLearnerRepository iRegisterAndLearnerRepository;

	private IApplicationRepository iApplicationRepository;

	public Task create(SaveTaskDto dto) {
		Task task = new Task();

		task = (Task) mapDtoToModel.map(dto, task);

		String responsCharactor = generateTaskCode();

		
		System.out.println(responsCharactor);
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
		List<Long> subjectIds = dto.getIdSubjects();
		List<Subject> subjects = new ArrayList<>();
		for (int i = 0; i < subjectIds.size(); i++) {
			Subject subject = iSubjectRepository.getOne(subjectIds.get(i));
			subjects.add(subject);

		}
		task.setSubjects(subjects);
		List<Long> requireIds = dto.getIdRequires();
		List<Require> requires = new ArrayList<>();
		for (int i = 0; i < requireIds.size(); i++) {
			Require require = iRequireRepository.getOne(requireIds.get(i));
			requires.add(require);
		}
		task.setRequires(requires);

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
			
			iTaskRepository.flush();
			
			return iTaskRepository.save(task);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private String generateTaskCode() {

	
			
			List<Task> listTask=iTaskRepository.findAll();
			
			
			
			int n = listTask.size();

			// lấy stt task id trước đó
			int count = 0;

			if (n > 0) {
				String dayEnd = listTask.get(n - 1).getId();// Lấy mã cuối ngày so sánh

				
				
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
		try {
			Task task = iTaskRepository.getOne(dto.getTaskId());

			switch (dto.getAttributeName()) {
			case "subjects":
				Subject subject = iSubjectRepository.getOne(dto.getObjectId());
				if (!task.getSubjects().contains(subject)) {
					task.addSubject(subject);
					task.setSubjectApperance(TaskAppearanceGenerator.generateSubjectAppearance(task.getSubjects()));
				}
				;
				break;
			default:
				break;
			}
			;

			return iTaskRepository.save(task);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Delete Object from Task
	@Override
	public Task deleteObject(AddObjectToTaskDto dto) {
		try {
			Task task = iTaskRepository.getOne(dto.getTaskId());

			switch (dto.getAttributeName()) {
			case "subjects":
				Subject subject = iSubjectRepository.getOne(dto.getObjectId());
				if (task.getSubjects().contains(subject)) {
					task.removeSubject(subject);
				}
				;
				break;
			default:
				break;
			}
			;

			return iTaskRepository.save(task);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Task updateSubject(UpdateSubjectDto dto) {

		Task task = iTaskRepository.getOne(dto.getId());

		List<Long> subjectIds = dto.getSubjectIds();
		List<Subject> subjects = new ArrayList<>();
		for (int i = 0; i < subjectIds.size(); i++) {
			Subject subject = iSubjectRepository.getOne(subjectIds.get(i));
			subjects.add(subject);

		}
		task.setSubjects(subjects);

		task.setSubjectApperance(dto.getSubjectApperance());

		task.setSubjectNote(dto.getSubjectNote());

		return iTaskRepository.save(task);
	}

	@Override
	public Task updateRequire(UpdateRequireDto dto) {

		Task task = iTaskRepository.getOne(dto.getId());

		List<Long> requireIds = dto.getRequireIds();
		List<Require> requires = new ArrayList<>();
		for (int i = 0; i < requireIds.size(); i++) {
			Require require = iRequireRepository.getOne(requireIds.get(i));
			requires.add(require);
		}
		task.setRequires(requires);

		task.setRequireApperance(dto.getRequireApperance());

		task.setRequireNote(dto.getRequireNote());

		return iTaskRepository.save(task);
	}

	@Override
	public Task UpdateLesson(UpdateLessonDto dto) {
		
		Task task = iTaskRepository.getOne(dto.getId());

		task.setLessonNumber(dto.getLessonNumber());
		task.setLessonNumberPerTime(dto.getLessonNumberPerTime());

		return iTaskRepository.save(task);

	}

	@Override
	public Task updateHour(UpdateHourDto dto) {
		
		Task task = iTaskRepository.getOne(dto.getId());

		task.setHour(dto.getHour());
		task.setHourPerTime(dto.getLessonNumberPerTime());

		return iTaskRepository.save(task);
	}

	@Override
	public Task updateFreeTime(UpdateFreeTimeDto dto) {
		Task task = iTaskRepository.getOne(dto.getId());

		task.setFreeTime(dto.getFreeTime());
		return iTaskRepository.save(task);
	}

	@Override
	public Task updateSalary(UpdateSalaryDto dto) {
		Task task = iTaskRepository.getOne(dto.getId());

		task.setSalary(dto.getSalary());
		
		task.setSalaryPerTime(dto.getSalaryPerTime());
		
		task.setUnitOfSalary(dto.getUnitOfSalary());

		return iTaskRepository.save(task);
	}

	@Override
	public Task updateTaskPlaceAddress(UpdateTaskPlaceAddresseDto dto) {
		
		
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
		
	}

}
