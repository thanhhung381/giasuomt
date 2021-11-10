package giasuomt.demo.task.service;

import java.util.LinkedList;
import java.util.List;

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

	@Override
	public List<Job> createAll(List<SaveJobDto> dtos) {
		try {

			List<Job> jobs = new LinkedList<>();
			for (SaveJobDto dto : dtos) {
				Job job = new Job();

				job = (Job) mapDtoToModel.map(dto, job);

				job.setApplication(iApplicationRepository.getOne(dto.getIdApplication()));

				job.setPerson(iPersonRepository.getOne(dto.getIdPerson()));

				job.setTask(iTaskRepository.getOne(dto.getIdTask()));
				jobs.add(job);
			}

			return iJobRepository.saveAll(jobs);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
