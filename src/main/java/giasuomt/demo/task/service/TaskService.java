package giasuomt.demo.task.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
import giasuomt.demo.task.dto.AddSubjectToTaskDto;
import giasuomt.demo.task.dto.SaveTaskDto;
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

		task.setId(TaskCodeGenerator.generatorCode().concat(responsCharactor));
		
		return save(dto, task);
	}

	public Task update(SaveTaskDto dto) {
		Task task = iTaskRepository.findByIdString(dto.getId());
		
		task =(Task) mapDtoToModel.map(dto, task);
		
		return save(dto, task);
	}

	private void mapDto(Task task, SaveTaskDto dto) {

		// String taskCode = dto.getId();

	
		
		List<Long> registerIds = dto.getIdRegisters();
		List<RegisterAndLearner> registers = new ArrayList<>();
		for (int i = 0; i < registerIds.size(); i++) {
			RegisterAndLearner register = iRegisterAndLearnerRepository.getOne(registerIds.get(i));
			registers.add(register);
		}
		task.setRegisters(registers);

		List<Long> LearnerIds = dto.getIdRegisters();
		List<RegisterAndLearner> learners = new ArrayList<>();
		for (int i = 0; i < LearnerIds.size(); i++) {
			RegisterAndLearner leaner = iRegisterAndLearnerRepository.getOne(LearnerIds.get(i));
			learners.add(leaner);
		}
		task.setLearners(learners);

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
			
			return iTaskRepository.save(task);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private String generateTaskCode() {

		int n = iTaskRepository.findAll().size();

		// lấy stt task id trước đó
		int count = 0;

		if (n > 0) {
			String dayEnd = iTaskRepository.findAll().get(n - 1).getId();// Lấy mã cuối ngày so sánh

			if (dayEnd != null) {
				count = TaskCodeGenerator.generateResponsiveReserve(dayEnd.substring(4, 6));
				// System.out.println(count);

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
			//	String responsCharactor = generateTaskCode();

//				task.setId(TaskCodeGenerator.generatorCode().concat(responsCharactor));
				
				
				//mã có sẵn
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

	
	//Add Subject to Task
	@Override
	public Task addSubjectToTask(AddSubjectToTaskDto dto) {
		try {
			Task task = iTaskRepository.getOne(dto.getTaskId());
			Subject subject = iSubjectRepository.getOne(dto.getSubjectId());
			if(!task.getSubjects().contains(subject)) {
				task.addSubject(subject);
				task.setSubjectApperance(TaskAppearanceGenerator.generateSubjectAppearance(task.getSubjects()));
				task = iTaskRepository.save(task);
			};	
			return task;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//Add Subject from Task
	@Override
	public Task deleteSubjectFromTask(AddSubjectToTaskDto dto) {
		try {
			Task task = iTaskRepository.getOne(dto.getTaskId());
			Subject subject = iSubjectRepository.getOne(dto.getSubjectId());
			if(task.getSubjects().contains(subject)) {
				task.removeSubject(subject);
			
				task = iTaskRepository.save(task);
			};	
			return task;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	//Tìm ra danh sách Registers và Learners theo Id
//	@Override
//	public List<RegisterAndLearner> findRegistersById(Long id) {
//		return iTaskRepository.findRegistersById(id);
//	}
//
//	@Override
//	public List<RegisterAndLearner> findLearnersById(Long id) {
//		return iTaskRepository.findLearnersById(id);
//	}

}
