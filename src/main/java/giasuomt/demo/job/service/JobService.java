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
import giasuomt.demo.person.model.GraduatedStudent;
import giasuomt.demo.person.model.InstitutionTeacher;
import giasuomt.demo.person.model.SchoolTeacher;
import giasuomt.demo.person.model.Student;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.person.model.Worker;
import giasuomt.demo.person.repository.ITutorRepository;
import giasuomt.demo.tags.model.TutorTag;
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
		Set<Long> retainedImgsIdentificationIds = dto.getRetainedImgsIdentificationId();
		Set<String> retainedImgsIdentification = new HashSet<>();

		for (Long retainedImgsIdentificationId : retainedImgsIdentificationIds) {

			RetainedImgsIdentificationAws avatar = iRetainedImgsIdentificationRepository
					.getOne(retainedImgsIdentificationId);
			
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
		Set<String> taskPlaceAddresss = new HashSet<>();
		for (TaskPlaceAddress taskPlaceAddress : iTaskRepository.getOne(dto.getTaskId()).getTaskPlaceAddresses()) {
			String attributeOfTaskPlaceTask = iTaskRepository.getOne(dto.getTaskId()).getTaskPlaceAddresses()
					.toString();
			System.out.println(attributeOfTaskPlaceTask);
			taskPlaceAddresss.add(attributeOfTaskPlaceTask);
		}
		taskByTheTimeCreatingJob.setTaskPlaceAddresses(taskPlaceAddresss);
		job.setTaskByTheTimeCreatingJob(taskByTheTimeCreatingJob);
		iTaskByTheTimeCreatingJobRepository.save(taskByTheTimeCreatingJob);

		// Tutor By Time Creating Job
		TutorByTheTimeCreatingJob tutorByTheTimeCreatingJob = new TutorByTheTimeCreatingJob();
		tutorByTheTimeCreatingJob = (TutorByTheTimeCreatingJob) mapDtoToModel
				.map(iTutorRepository.getOne(dto.getTutorId()), tutorByTheTimeCreatingJob);
		tutorByTheTimeCreatingJob.setId(dto.getTutorId());
		// Tutor Tags
		Set<String> tutorTags=new HashSet<>();
		for (TutorTag tutortag : iTutorRepository.getOne(dto.getTutorId()).getTutorTags()) {
			String attributeOfTutorTags = tutortag.toString();
			tutorTags.add(attributeOfTutorTags);
		}
		tutorByTheTimeCreatingJob.setTutorTags(tutorTags);
		
		
		
		// students
		Set<String> students = new HashSet<>();
		for (Student student : iTutorRepository.getOne(dto.getTutorId()).getStudents()) {
			String attributeOfStudent = student.toString();
					students.add(attributeOfStudent);
		}
		tutorByTheTimeCreatingJob.setStudents(students);
	//	List<String> students = new LinkedList<>();
	//	for (int i = 0; i < iTutorRepository.getOne(dto.getTutorId()).getStudents().size(); i++) {
	//		String attributeOfStudent = iTutorRepository.getOne(dto.getTutorId()).getStudents().get(i).toString();
	//		students.add(attributeOfStudent);
	//	}
	//	tutorByTheTimeCreatingJob.setStudents(students);
		// Instituton Teacher
		Set<String> institutionTeachers = new HashSet<>();
		
		for (InstitutionTeacher  institutionTeacher: iTutorRepository.getOne(dto.getTutorId()).getInstitutionTeachers()) {
			
			String attributeOfInstitution = institutionTeacher.toString();
			institutionTeachers.add(attributeOfInstitution);
		}
		tutorByTheTimeCreatingJob.setInstitutionTeachers(institutionTeachers);
		
		// Graduated Student
		Set<String> graduatedStudents = new HashSet<>();
		for (GraduatedStudent graduatedStudent : iTutorRepository.getOne(dto.getTutorId()).getGraduatedStudents()) {
			String attributeOfGraduatedStudent = graduatedStudent.toString();
			graduatedStudents.add(attributeOfGraduatedStudent);
		}
		tutorByTheTimeCreatingJob.setGraduatedStudents(graduatedStudents);
		
		
		// School Teacher
		Set<String> schoolTeachers = new HashSet<>();
		
		for (SchoolTeacher schoolTeacher : iTutorRepository.getOne(dto.getTutorId()).getSchoolTeachers()) {
			String attributeOfSchoolTeacher = schoolTeacher
					.toString();
			schoolTeachers.add(attributeOfSchoolTeacher);
		}
		
	
		// Workers
		Set<String> workers = new HashSet<>();
		for (Worker worker : iTutorRepository.getOne(dto.getTutorId()).getWorkers()) {
			String attributeOfWorker = worker.toString();
			workers.add(attributeOfWorker);
		}
		tutorByTheTimeCreatingJob.setWorkers(workers);
//		List<String> workers = new LinkedList<>();
//		for (int i = 0; i < iTutorRepository.getOne(dto.getTutorId()).getWorkers().size(); i++) {
//			String attributeOfWorker = iTutorRepository.getOne(dto.getTutorId()).getWorkers().get(i).toString();
//			workers.add(attributeOfWorker);
//		}
		//tutorByTheTimeCreatingJob.setWorkers(workers);

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

		job.setTutor(iTutorRepository.getOne(dto.getTutorId()));

		job.setTask(iTaskRepository.getOne(dto.getTaskId()));

		return save(dto, job);
	}

}
