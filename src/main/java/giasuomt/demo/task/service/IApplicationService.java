package giasuomt.demo.task.service;
import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.task.dto.SaveApplicationDto;
import giasuomt.demo.task.dto.SaveTutorCreateDto;
import giasuomt.demo.task.dto.UpdateApplicationSignDto;
import giasuomt.demo.task.model.Application;

public interface IApplicationService extends IGenericService<SaveApplicationDto, Application, Long>{
	void deleteByIdAppliction(Long id);
	
	Application updateApplicationSign(UpdateApplicationSignDto dto);

	Application tutorCreate(SaveTutorCreateDto dto);
}
