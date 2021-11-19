package giasuomt.demo.tags.service;
import javax.validation.Valid;
import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.tags.dto.SaveTutorTagDto;
import giasuomt.demo.tags.model.TutorTag;

public interface ITutorTagService extends IGenericService<SaveTutorTagDto, TutorTag, Long> {
	public TutorTag create(@Valid SaveTutorTagDto dto);

	public TutorTag save(SaveTutorTagDto dto, TutorTag TutorTag);

	public TutorTag update(SaveTutorTagDto dto);

	public boolean checkExistIdofTutorTag(Long id);

	public void deleteById(Long id);
}