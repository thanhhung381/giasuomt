package giasuomt.demo.tutor.service;
import java.util.List;
import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.tutor.dto.SaveTutorDto;
import giasuomt.demo.tutor.dto.TutorWithStudent;
import giasuomt.demo.tutor.model.Tutor;

public interface ITutorService extends IGenericService<Tutor, Long>  {
	public List<TutorWithStudent> findalll();

	public void delete(Long id);

	public Tutor create(SaveTutorDto dto);

	public Tutor update(SaveTutorDto dto);

	public Tutor save(SaveTutorDto dto, Tutor tutor);

}
