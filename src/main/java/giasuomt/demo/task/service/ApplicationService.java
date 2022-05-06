package giasuomt.demo.task.service;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import giasuomt.demo.comment.repository.IApplicationCommentRepository;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.person.Ultils.UpdateSubjectGroupMaybeAndSure;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.person.repository.ITutorRepository;
import giasuomt.demo.task.dto.SaveApplicationDto;
import giasuomt.demo.task.dto.SaveTutorCreateDto;
import giasuomt.demo.task.dto.UpdateApplicationSignDto;
import giasuomt.demo.task.model.Application;
import giasuomt.demo.task.model.Task;
import giasuomt.demo.task.repository.IApplicationRepository;
import giasuomt.demo.task.repository.ITaskRepository;
import giasuomt.demo.task.util.ApplicationSign;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ApplicationService extends GenericService<SaveApplicationDto, Application, Long> implements IApplicationService {

	private MapDtoToModel mapDtoToModel;

	private ITaskRepository iTaskRepository;

	private IApplicationRepository iApplicationRepository;

	private ITutorRepository iTutorRepository;
	
	private IApplicationCommentRepository iApplicationCommentRepository;

	public Application create(SaveApplicationDto dto) {

		Application application = new Application();

		application = (Application) mapDtoToModel.map(dto, application);

		Task task = iTaskRepository.findByIdString(dto.getTaskId());

		application.setTask(task);

		application.setTutor(iTutorRepository.getOne(dto.getTutorId()));
		
		
		application=save(dto,application);

		
		//lấy tutor chứa trong application đã lưu
		
		Tutor tutor=iTutorRepository.getOne(application.getTutor().getId());
		
		tutor=  UpdateSubjectGroupMaybeAndSure.generateSubjectGroupMaybeInTutor(tutor);
		
		iTutorRepository.save(tutor);
		
		return application;
	}

	@Override
	public Application update(SaveApplicationDto dto) {

		Application application = iApplicationRepository.getOne(dto.getId());

		application = (Application) mapDtoToModel.map(dto, application);

		application.setTask(iTaskRepository.getOne(dto.getTaskId()));

		application.setTutor(iTutorRepository.getOne(dto.getTutorId()));

		return save(dto, application);

	}

	@Override
	public List<Application> createAll(List<SaveApplicationDto> dtos) {
		try {

			List<Application> applications = new LinkedList<>();
			for (SaveApplicationDto dto : dtos) {
				Application application = new Application();

				application = (Application) mapDtoToModel.map(dto, application);

				application.setTask(iTaskRepository.getOne(dto.getTaskId()));

				application.setTutor(iTutorRepository.getOne(dto.getTutorId()));

				applications.add(application); 


			}
			return iApplicationRepository.saveAll(applications);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteByIdAppliction(Long id) {
		iApplicationCommentRepository.deleteAll(iApplicationRepository.getOne(id).getComments());//xóa application là xóa hết
		iApplicationRepository.deleteById(id);
		
	}

	@Override
	public Application updateApplicationSign(UpdateApplicationSignDto dto) {
		try {
			
			Application application=iApplicationRepository.getOne(dto.getId());
			
			Set<ApplicationSign> applications=new HashSet<>();
			for(ApplicationSign applicationSign :dto.getApplicationSigns())
			{
				applications.add(applicationSign);
			}
			
			application.setApplicationSigns(applications);

			
			return iApplicationRepository.save(application);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Application tutorCreate(SaveTutorCreateDto dto) {
		Application application=new Application();
		
		try {
			application = (Application) mapDtoToModel.map(dto, application);
			
			
			application.setTask(iTaskRepository.getOne(dto.getIdTask()));
			
			application=iApplicationRepository.save(application);
			
			application.setTutor(iTutorRepository.findByCreatedBy(application.getCreatedBy()));
			
			return iApplicationRepository.save(application);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

}
