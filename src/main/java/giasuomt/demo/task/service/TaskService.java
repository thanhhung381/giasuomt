package giasuomt.demo.task.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.educational.model.Subject;
import giasuomt.demo.educational.repository.ISubjectRepository;
import giasuomt.demo.location.repository.IAreaRepository;
import giasuomt.demo.task.dto.SaveTaskDto;
import giasuomt.demo.task.dto.SaveTaskPlaceAddressDto;
import giasuomt.demo.task.model.Require;
import giasuomt.demo.task.model.Task;
import giasuomt.demo.task.model.TaskPlaceAddress;
import giasuomt.demo.task.repository.IRequireRepository;
import giasuomt.demo.task.repository.ITaskPlaceAddressRepository;
import giasuomt.demo.task.repository.ITaskRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskService extends GenericService<SaveTaskDto,Task, Long> implements ITaskService {
	
	private ITaskRepository iTaskRepository;

	private MapDtoToModel mapDtoToModel;
	
	private IAreaRepository iAreaRepository;
	
	private ITaskPlaceAddressRepository iTaskPlaceAddressRepository;
	
	private ISubjectRepository iSubjectRepository;
	
	private IRequireRepository iRequireRepository;

	public Task create(SaveTaskDto dto) {
		Task task=new Task();
		return save(dto, task);
	}

	
	public Task update(SaveTaskDto dto) {
		Task task=iTaskRepository.getOne(dto.getId());
		return save(dto, task);
	}
	
	public Task save(SaveTaskDto dto,Task task)
	{
		
		try {
			task=(Task) mapDtoToModel.map(dto, task);
				
			
			//Subject vì sau khi nhập subject thì đã có tồn tại subjectgroup rồi
			List<Long> subjectIds=dto.getIdSubjects();
			List<Subject> subjects=new ArrayList<>();
			for(int i=0;i<subjectIds.size();i++)
			{
				Subject subject =iSubjectRepository.getOne(subjectIds.get(i));
				subjects.add(subject);
						
			}
			task.setSubjects(subjects);
			List<Long> requireIds=dto.getIdRequires();
			List<Require> requires=new ArrayList<>();
			for(int i=0;i<requireIds.size();i++)
			{
				Require require=iRequireRepository.getOne(requireIds.get(i));
				requires.add(require);
			}
			task.setRequires(requires);
			
			
			
			
			
			List<SaveTaskPlaceAddressDto> saveTaskPlaceAddressDtos=dto.getSaveTaskPlaceAddressDtos();
			for(int i=0;i< task.getTaskPlaceAddresses().size() ;i++)
			{
				boolean deleteThis=true;
				for(int j=0;j<saveTaskPlaceAddressDtos.size();j++)
				{
					if(task.getTaskPlaceAddresses().get(i).getId()==saveTaskPlaceAddressDtos.get(j).getId())
					{
						deleteThis=false;
					}
				}
				if(deleteThis)
				{
					task.removeTaskPlaceAddress(task.getTaskPlaceAddresses().get(i));
					i--;
				}
			}
			for(int i=0;i<saveTaskPlaceAddressDtos.size();i++)
			{
				SaveTaskPlaceAddressDto placeAddressDto=saveTaskPlaceAddressDtos.get(i);
				if(placeAddressDto.getId()!=null && placeAddressDto.getId()>0)
				{
					TaskPlaceAddress taskPlaceAddress=iTaskPlaceAddressRepository.getOne(placeAddressDto.getId());
						taskPlaceAddress=(TaskPlaceAddress)mapDtoToModel.map(placeAddressDto, taskPlaceAddress);
						taskPlaceAddress.setArea(iAreaRepository.getOne(placeAddressDto.getIdArea()));
						task.addTaskPlaceAddress(taskPlaceAddress);
				}
				else
				{
					TaskPlaceAddress taskPlaceAddress=new TaskPlaceAddress();
					taskPlaceAddress=(TaskPlaceAddress)mapDtoToModel.map(placeAddressDto, taskPlaceAddress);
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
	
	


}
