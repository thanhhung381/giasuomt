package giasuomt.demo.job.service;

import java.util.LinkedList;

import java.util.List;

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
import giasuomt.demo.person.Ultils.ExperienceForTutor;
import giasuomt.demo.person.Ultils.UpdateSubjectGroupMaybeAndSure;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.person.repository.ITutorRepository;
import giasuomt.demo.task.repository.IApplicationRepository;
import giasuomt.demo.task.repository.ITaskRepository;
import giasuomt.demo.uploadfile.model.RetainedImgsIdentificationAws;
import giasuomt.demo.uploadfile.repository.IRetainedImgsIdentificationAwsRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JobService extends GenericService<SaveJobDto, Job, Long> implements IJobService {

	private IJobRepository iJobRepository;

	private IApplicationRepository iApplicationRepository;

	private IRetainedImgsIdentificationAwsRepository iRetainedImgsIdentificationRepository;

	private ITutorRepository iTutorRepository;

	private ITaskRepository iTaskRepository;

	private MapDtoToModel mapDtoToModel;

	private ITutorByTheTimeCreatingJobRepository iTutorByTheTimeCreatingJobRepository;

	private ITaskByTheTimeCreatingJobRepository iTaskByTheTimeCreatingJobRepository;

	public Job create(SaveJobDto dto) {

		Job job = new Job();

		job.setApplication(iApplicationRepository.getOne(dto.getApplicationId()));

		job.setTutor(iTutorRepository.getOne(dto.getTutorId()));

		job.setTask(iTaskRepository.getOne(dto.getTaskId()));

		return save(dto, job);
	}

	private void mapDto(SaveJobDto dto, Job job) {
		job = (Job) mapDtoToModel.map(dto, job);

		// thêm ảnh
		List<Long> retainedImgsIdentificationId = dto.getRetainedImgsIdentificationId();
		List<String> retainedImgsIdentification = new LinkedList<>();

		for (int i = 0; i < retainedImgsIdentificationId.size(); i++) {

			RetainedImgsIdentificationAws avatar = iRetainedImgsIdentificationRepository
					.getOne(retainedImgsIdentificationId.get(i));
			
			retainedImgsIdentification.add(avatar.getUrlRetainedImgsIdentification());

		}
		job.setRetainedImgsIdentification(retainedImgsIdentification);

		// Subject Group Sure

		// save Task Time Creating Job
		TaskByTheTimeCreatingJob taskByTheTimeCreatingJob = new TaskByTheTimeCreatingJob();
		taskByTheTimeCreatingJob = (TaskByTheTimeCreatingJob) mapDtoToModel.map(iTaskRepository.getOne(dto.getTaskId()),
				taskByTheTimeCreatingJob);
		taskByTheTimeCreatingJob.setId(dto.getTaskId());
		// take place address
		List<String> taskPlaceAddress = new LinkedList<>();
		for (int i = 0; i < iTaskRepository.getOne(dto.getTaskId()).getTaskPlaceAddresses().size(); i++) {
			String attributeOfTaskPlaceTask = iTaskRepository.getOne(dto.getTaskId()).getTaskPlaceAddresses().get(i)
					.toString();
			System.out.println(attributeOfTaskPlaceTask);
			taskPlaceAddress.add(attributeOfTaskPlaceTask);
		}
		taskByTheTimeCreatingJob.setTaskPlaceAddresses(taskPlaceAddress);
		job.setTaskByTheTimeCreatingJob(taskByTheTimeCreatingJob);
		iTaskByTheTimeCreatingJobRepository.save(taskByTheTimeCreatingJob);

		// Tutor By Time Creating Job
		TutorByTheTimeCreatingJob tutorByTheTimeCreatingJob = new TutorByTheTimeCreatingJob();
		tutorByTheTimeCreatingJob = (TutorByTheTimeCreatingJob) mapDtoToModel
				.map(iTutorRepository.getOne(dto.getTutorId()), tutorByTheTimeCreatingJob);
		tutorByTheTimeCreatingJob.setId(dto.getTutorId());
		// Tutor Tags
		List<String> tutorTags = new LinkedList<>();
		for (int i = 0; i < iTutorRepository.getOne(dto.getTutorId()).getTutorTags().size(); i++) {
			String attributeOfTutorTags = iTutorRepository.getOne(dto.getTutorId()).getTutorTags().get(i).toString();
			tutorTags.add(attributeOfTutorTags);
		}
		tutorByTheTimeCreatingJob.setTutorTags(tutorTags);
		// students
		List<String> students = new LinkedList<>();
		for (int i = 0; i < iTutorRepository.getOne(dto.getTutorId()).getStudents().size(); i++) {
			String attributeOfStudent = iTutorRepository.getOne(dto.getTutorId()).getStudents().get(i).toString();
			students.add(attributeOfStudent);
		}
		tutorByTheTimeCreatingJob.setStudents(students);
		// Instituton Teacher
		List<String> institutionTeachers = new LinkedList<>();
		for (int i = 0; i < iTutorRepository.getOne(dto.getTutorId()).getInstitutionTeachers().size(); i++) {
			String attributeOfInstitution = iTutorRepository.getOne(dto.getTutorId()).getInstitutionTeachers().get(i)
					.toString();
			institutionTeachers.add(attributeOfInstitution);
		}
		tutorByTheTimeCreatingJob.setInstitutionTeachers(institutionTeachers);
		// Graduated Student
		List<String> graduatedStudents = new LinkedList<>();
		for (int i = 0; i < iTutorRepository.getOne(dto.getTutorId()).getGraduatedStudents().size(); i++) {
			String attributeOfGraduatedStudent = iTutorRepository.getOne(dto.getTutorId()).getGraduatedStudents().get(i)
					.toString();
			graduatedStudents.add(attributeOfGraduatedStudent);
		}
		tutorByTheTimeCreatingJob.setGraduatedStudents(graduatedStudents);
		// School Teacher
		List<String> schoolTeachers = new LinkedList<>();
		for (int i = 0; i < iTutorRepository.getOne(dto.getTutorId()).getSchoolTeachers().size(); i++) {
			String attributeOfSchoolTeacher = iTutorRepository.getOne(dto.getTutorId()).getSchoolTeachers().get(i)
					.toString();
			schoolTeachers.add(attributeOfSchoolTeacher);
		}
		tutorByTheTimeCreatingJob.setSchoolTeachers(schoolTeachers);
		// Workers
		List<String> workers = new LinkedList<>();
		for (int i = 0; i < iTutorRepository.getOne(dto.getTutorId()).getWorkers().size(); i++) {
			String attributeOfWorker = iTutorRepository.getOne(dto.getTutorId()).getWorkers().get(i).toString();
			workers.add(attributeOfWorker);
		}
		tutorByTheTimeCreatingJob.setWorkers(workers);

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
	//		}

			

			return job;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Job updateJobResult(UpdateJobResultDto dto) {
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

	}

	@Override
	public Job update(SaveJobDto dto) {

		return null;
	}

}
