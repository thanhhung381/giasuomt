package giasuomt.demo.task.service;

import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.job.model.Job;
import giasuomt.demo.person.repository.IPersonRepository;
import giasuomt.demo.task.dto.SaveJobDto;
import giasuomt.demo.task.repository.IApplicationRepository;
import giasuomt.demo.task.repository.IJobRepository;
import giasuomt.demo.task.repository.ITaskRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JobService extends GenericService<SaveJobDto, Job, Long> implements IJobService {

	private MapDtoToModel mapDtoToModel;

	private ITaskRepository iTaskRepository;

	private IPersonRepository iPersonRepository;

	private IApplicationRepository iApplicationRepository;

	private IJobRepository iJobRepository;

	public Job create(SaveJobDto dto) {

		Job job = new Job();

		job = (Job) mapDtoToModel.map(dto, job);

		job.setApplication(iApplicationRepository.getOne(dto.getIdApplication()));

		job.setPerson(iPersonRepository.getOne(dto.getIdPerson()));

		job.setTask(iTaskRepository.getOne(dto.getIdTask()));

		return save(dto, job);
	}

	@Override
	public Job update(SaveJobDto dto) {

		Job job = iJobRepository.getOne(dto.getId());

		job = (Job) mapDtoToModel.map(dto, job);

		job.setApplication(iApplicationRepository.getOne(dto.getIdApplication()));

		job.setPerson(iPersonRepository.getOne(dto.getIdPerson()));

		job.setTask(iTaskRepository.getOne(dto.getIdTask()));

		return save(dto, job);
	}

}
