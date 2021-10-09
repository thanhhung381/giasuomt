package giasuomt.demo.tutor.service;
import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.tutor.dto.SaveTutorDto;
import giasuomt.demo.tutor.model.Tutor;

public interface ITutorService extends IGenericService<Tutor, Long>  {
	
	public Tutor create(SaveTutorDto dto);

	public Tutor update(SaveTutorDto dto);

	public Tutor save(SaveTutorDto dto, Tutor tutor);
	
	public void delete(Long id);

}
