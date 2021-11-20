package giasuomt.demo.task.service;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
import giasuomt.demo.task.dto.SaveTaskDto;
import giasuomt.demo.task.model.Require;
import giasuomt.demo.task.model.Task;
import giasuomt.demo.task.repository.IApplicationRepository;
import giasuomt.demo.task.repository.IRequireRepository;
import giasuomt.demo.task.repository.ITaskRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskService extends GenericService<SaveTaskDto, Task, Long> implements ITaskService {

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

		return save(dto, task);
	}

	public Task update(SaveTaskDto dto) {
		Task task = iTaskRepository.getOne(dto.getId());
		return save(dto, task);
	}

	private void mapDto(Task task, SaveTaskDto dto) {
		
		String taskCode=dto.getTaskCode();
		
		task = (Task) mapDtoToModel.map(dto, task);
		if(taskCode.contains("NewTask") && taskCode!=null)
		{
			// generate code

			
			String responsCharactor = generateTaskCode();

			task.setTaskCode(TaskCodeGenerator.generatorCode().concat(responsCharactor));
			
		}
		else
			task.setTaskCode(taskCode);
		
		

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

	private boolean checkExistID(Long id) {
		return iTaskRepository.countById(id) == 1;
	}

	private String generateTaskCode() {

		String dayEnd = iTaskRepository.getTaskCodeEndOfDay();// Lấy mã cuối ngày so sánh

		// lấy stt task id trước đó
		int count = 0;

		if (dayEnd != null) {
			count = TaskCodeGenerator.generateResponsiveReserve(dayEnd.substring(4, 6));
			System.out.println(count);

			if (TaskCodeGenerator.AutoGennerate(dayEnd) == -1 || TaskCodeGenerator.AutoGennerate(dayEnd) == 2)// check
																												// // ko
			{
				count = 1;

			} else if (TaskCodeGenerator.AutoGennerate(dayEnd) == 3) {
				count += 1;

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
				String responsCharactor = generateTaskCode();

				task.setTaskCode(TaskCodeGenerator.generatorCode().concat(responsCharactor));
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
	public Task findByTaskCode(String taskCode) {
		
		return iTaskRepository.findByTaskCode(taskCode);
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
