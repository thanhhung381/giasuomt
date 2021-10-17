package giasuomt.demo.task.service;

import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.task.dto.SaveApplicationDto;
import giasuomt.demo.task.model.Application;
import giasuomt.demo.task.repository.IApplicationRepository;
import giasuomt.demo.task.repository.ITaskRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
@AllArgsConstructor
public class ApplicationService extends GenericService<SaveApplicationDto, Application, Long>
		implements IApplicationService {

	private MapDtoToModel mapDtoToModel;

	private ITaskRepository iTaskRepository;

	private IApplicationRepository iApplicationRepository;

	public Application create(SaveApplicationDto dto) {

		Application application = new Application();

		application = (Application) mapDtoToModel.map(dto, application);

		application.setTask(iTaskRepository.getById(dto.getIdTask()));

		return save(dto, application);
	}

	@Override
	public Application update(SaveApplicationDto dto) {

		Application application = iApplicationRepository.getById(dto.getId());

		application = (Application) mapDtoToModel.map(dto, application);

		application.setTask(iTaskRepository.getById(dto.getIdTask()));

		return save(dto, application);

	}

}
