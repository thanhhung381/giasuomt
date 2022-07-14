package giasuomt.demo.task.service;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.person.repository.IRegisterAndLearnerRepository;
import giasuomt.demo.task.dto.SaveRegistrationDto;
import giasuomt.demo.task.model.Registration;
import giasuomt.demo.task.model.Task;
import giasuomt.demo.task.repository.IRegistrationRepository;
import giasuomt.demo.task.repository.ITaskRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService extends GenericService<SaveRegistrationDto, Registration, Long> implements IRegistrationService{
	private MapDtoToModel mapDtoToModel;

	private ITaskRepository iTaskRepository;

	private IRegistrationRepository iRegistrationRepository;

	private IRegisterAndLearnerRepository iRegisterAndLearnerRepository;

	public Registration create(SaveRegistrationDto dto) {

		Registration registration = new Registration();

		registration = (Registration) mapDtoToModel.map(dto, registration);

		Task task = iTaskRepository.findByIdString(dto.getTaskId());

		registration.setTask(task);

		registration.setRegisterAndLearner(iRegisterAndLearnerRepository.getOne(dto.getRegisterAndLearnerId()));

		return save(dto, registration);
	}

	@Override
	public Registration update(SaveRegistrationDto dto) {

		Registration registration = iRegistrationRepository.getOne(dto.getId());

		registration = (Registration) mapDtoToModel.map(dto, registration);

		registration.setTask(iTaskRepository.getOne(dto.getTaskId()));

		registration.setRegisterAndLearner(iRegisterAndLearnerRepository.getOne(dto.getRegisterAndLearnerId()));

		return save(dto, registration);

	}

	@Override
	public Set<Registration> createAll(Set<SaveRegistrationDto> dtos) {
		try {

			Set<Registration> registrations = new HashSet<>();
			for (SaveRegistrationDto dto : dtos) {
				Registration registration = new Registration();

				registration = (Registration) mapDtoToModel.map(dto, registration);

				registration.setTask(iTaskRepository.getOne(dto.getTaskId()));

				registration.setRegisterAndLearner(iRegisterAndLearnerRepository.getOne(dto.getRegisterAndLearnerId()));

				registrations.add(registration); 


			}
			return Sets.newHashSet(iRegistrationRepository.saveAll(registrations));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
