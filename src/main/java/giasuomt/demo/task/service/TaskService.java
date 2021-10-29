package giasuomt.demo.task.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generator.TaskCodeGenerator;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.educational.model.Subject;
import giasuomt.demo.educational.repository.ISubjectRepository;
import giasuomt.demo.location.repository.IAreaRepository;
import giasuomt.demo.person.model.Person;
import giasuomt.demo.person.repository.IPersonRepository;
import giasuomt.demo.task.dto.SaveTaskDto;
import giasuomt.demo.task.dto.SaveTaskPlaceAddressDto;
import giasuomt.demo.task.model.Require;
import giasuomt.demo.task.model.Task;
import giasuomt.demo.task.model.TaskPlaceAddress;
import giasuomt.demo.task.repository.IRequireRepository;
import giasuomt.demo.task.repository.ITaskPlaceAddressRepository;
import giasuomt.demo.task.repository.ITaskRepository;
import io.netty.handler.codec.string.StringDecoder;
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

	private IPersonRepository iPersonRepository;

	public Task create(SaveTaskDto dto) {
		Task task = new Task();

		return save(dto, task);
	}

	public Task update(SaveTaskDto dto) {
		Task task = iTaskRepository.getOne(dto.getId());
		return save(dto, task);
	}

	public Task save(SaveTaskDto dto, Task task) {

		try {
			task = (Task) mapDtoToModel.map(dto, task);

			String dayEnd = iTaskRepository.getTaskCodeEndOfDay();// Lấy mã cuối ngày so sánh

			Integer noOfTaskBefore = iTaskRepository.findNoOfTaskById(iTaskRepository.getMaxId());
			// lấy stt task id trước đó

			if (noOfTaskBefore == null || TaskCodeGenerator.AutoGennerate(dayEnd) == -1
					|| TaskCodeGenerator.AutoGennerate(dayEnd) == 2)// check xe nos cos ton ai hay ko
			{
				noOfTaskBefore = 1;
				task.setNoOfTask(1);
			} else if (TaskCodeGenerator.AutoGennerate(dayEnd) == 3) {
				noOfTaskBefore += 1;
				task.setNoOfTask(noOfTaskBefore);

			}

			String responsCharactor = TaskCodeGenerator.generateResponsive((int) noOfTaskBefore);

			task.setTaskCode(TaskCodeGenerator.generatorCode().concat(responsCharactor));

			List<Long> registerIds = dto.getIdRegisters();
			List<Person> registers = new ArrayList<>();
			for (int i = 0; i < registerIds.size(); i++) {
				Person register = iPersonRepository.getOne(registerIds.get(i));
				registers.add(register);
			}
			task.setRegisters(registers);

			List<Long> LearnerIds = dto.getIdRegisters();
			List<Person> learners = new ArrayList<>();
			for (int i = 0; i < LearnerIds.size(); i++) {
				Person leaner = iPersonRepository.getOne(LearnerIds.get(i));
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

			return iTaskRepository.save(task);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private boolean checkExistID(Long id) {
		return iTaskRepository.countById(id) == 1;
	}

}
