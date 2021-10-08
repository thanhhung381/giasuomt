package giasuomt.demo.tutor.service;

import java.util.List;
import java.util.Set;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.location.dto.FindingDtoArea;
import giasuomt.demo.location.dto.UpdateAreaDTO;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.tutor.dto.CreateTutorDto;
import giasuomt.demo.tutor.dto.TutorWithStudent;
import giasuomt.demo.tutor.dto.UpdateTutorDto;
import giasuomt.demo.tutor.model.Student;
import giasuomt.demo.tutor.model.Tutor;

public interface ITutorService extends IGenericService<Tutor, Long>  {
	public Tutor save(CreateTutorDto dto);
	public List<TutorWithStudent> findalll();
	Tutor update(UpdateTutorDto dto, Long id);
	void deleteById(Long idTutor);
	
	
	
}
