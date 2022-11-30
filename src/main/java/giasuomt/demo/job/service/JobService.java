package giasuomt.demo.job.service;

import java.util.HashSet;

import java.util.LinkedList;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.job.dto.SaveJobDto;
import giasuomt.demo.job.dto.UpdateJobResultDto;
import giasuomt.demo.job.model.Job;
import giasuomt.demo.job.model.TaskByTheTimeCreatingJob;
import giasuomt.demo.job.model.TutorByTheTimeCreatingJob;
import giasuomt.demo.job.repository.IJobRepository;
import giasuomt.demo.job.repository.ITaskByTheTimeCreatingJobRepository;
import giasuomt.demo.job.repository.ITutorByTheTimeCreatingJobRepository;
import giasuomt.demo.location.model.TaskPlaceAddress;
import giasuomt.demo.person.Ultils.ExperienceForTutor;
import giasuomt.demo.person.Ultils.UpdateSubjectGroupMaybeAndSure;
import giasuomt.demo.person.model.Tutor;

import giasuomt.demo.person.repository.ITutorRepository;
import giasuomt.demo.tags.model.TutorTag;
import giasuomt.demo.task.model.Application;
import giasuomt.demo.task.repository.IApplicationRepository;
import giasuomt.demo.task.repository.ITaskRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JobService extends GenericService<SaveJobDto, Job, String> implements IJobService {

	private IJobRepository iJobRepository;
	private IApplicationRepository iApplicationRepository;
	private ITutorRepository iTutorRepository;
	private ITaskRepository iTaskRepository;
	private MapDtoToModel mapDtoToModel;
	private ITutorByTheTimeCreatingJobRepository iTutorByTheTimeCreatingJobRepository;
	private ITaskByTheTimeCreatingJobRepository iTaskByTheTimeCreatingJobRepository;
	


	public Job create(SaveJobDto dto) {
		Job job = new Job();
		Application application = iApplicationRepository.getOne(dto.getApplicationId());
		job.setApplication(application);
		return save(dto, job);
	}

	private void mapDto(SaveJobDto dto, Job job) {
		job = (Job) mapDtoToModel.map(dto, job);
		Long tutorId = Long.parseLong(dto.getApplicationId().substring(dto.getApplicationId().indexOf("-") + 1) );
		String taskId =dto.getApplicationId().substring(0, dto.getApplicationId().indexOf("-"));
		System.out.println(taskId);
		job.setTutor(iTutorRepository.getOne(tutorId));
		job.setTask(iTaskRepository.getOne(taskId));
		// Subject Group Sure
		// save Task Time Creating Job
		TaskByTheTimeCreatingJob taskByTheTimeCreatingJob = new TaskByTheTimeCreatingJob();
		taskByTheTimeCreatingJob = (TaskByTheTimeCreatingJob) mapDtoToModel.map(iTaskRepository.getOne(taskId),
				taskByTheTimeCreatingJob);
		taskByTheTimeCreatingJob.setId(taskId);
		// take place address
		Set<String> taskPlaceAddresss = new HashSet<>();
		if(iTaskRepository.getOne(taskId).getTaskPlaceAddresses() != null) {
			for (TaskPlaceAddress taskPlace : iTaskRepository.getOne(taskId).getTaskPlaceAddresses()) {
				String attributeOfTaskPlaceTask = taskPlace
						.toString();
				taskPlaceAddresss.add(attributeOfTaskPlaceTask);
			}
			taskByTheTimeCreatingJob.setTaskPlaceAddresses(taskPlaceAddresss);
			job.setTaskByTheTimeCreatingJob(taskByTheTimeCreatingJob);
			iTaskByTheTimeCreatingJobRepository.save(taskByTheTimeCreatingJob);
		}
	
//		// Tutor By Time Creating Job
		TutorByTheTimeCreatingJob tutorByTheTimeCreatingJob = new TutorByTheTimeCreatingJob();
		tutorByTheTimeCreatingJob = (TutorByTheTimeCreatingJob) mapDtoToModel
				.map(iTutorRepository.getOne(tutorId), tutorByTheTimeCreatingJob);
		tutorByTheTimeCreatingJob.setId(tutorId);
		// Tutor Tags
		Set<String> tutorTags = new HashSet<>();
		for (TutorTag tutortag : iTutorRepository.getOne(tutorId).getTutorTags()) {
			String attributeOfTutorTags = tutortag.toString();
			tutorTags.add(attributeOfTutorTags);
		}
		tutorByTheTimeCreatingJob.setTutorTags(tutorTags);
		job.setId(dto.getApplicationId().concat("-job"));
		job.setTutorByTheTimeCreatingJob(tutorByTheTimeCreatingJob);
		iTutorByTheTimeCreatingJobRepository.save(tutorByTheTimeCreatingJob);

	}

	public Job save(SaveJobDto dto, Job job) {
		try {
			mapDto(dto, job);
			job = iJobRepository.save(job);
//			if(job.getJobResult()!=null)
//			{
//				Tutor tutor = iTutorRepository.getOne(job.getTutor().getId());

//				tutor = UpdateSubjectGroupMaybeAndSure.generateSubjectGroupSureInTutor(tutor);

//				iTutorRepository.save(tutor);
			// }
			return job;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Job updateJobResult(UpdateJobResultDto dto) {
		try {
			Job job = iJobRepository.getOne(dto.getId());
			job.setJobResult(dto.getJobResult());
			job.setFailReason(dto.getFailReason());
			job.setFindAnotherTutorIfFail(dto.getFindAnotherTutorIfFail());
			job = iJobRepository.save(job);
			Tutor tutor = iTutorRepository.getOne(job.getTutor().getId());
			tutor = ExperienceForTutor.updateExpForTutor(tutor);
			tutor = UpdateSubjectGroupMaybeAndSure.generateSubjectGroupSureInTutor(tutor);
			iTutorRepository.save(tutor);
			return job;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Job update(SaveJobDto dto) {
		Job job = iJobRepository.getOne(dto.getId());
		job.setApplication(iApplicationRepository.getOne(dto.getApplicationId()));
		return save(dto, job);
	}

}
