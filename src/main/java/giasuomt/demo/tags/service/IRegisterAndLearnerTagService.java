package giasuomt.demo.tags.service;
import javax.validation.Valid;
import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.tags.dto.SaveRegisterAndLearnerTagDto;
import giasuomt.demo.tags.model.RegisterAndLearnerTag;

public interface IRegisterAndLearnerTagService extends IGenericService<SaveRegisterAndLearnerTagDto, RegisterAndLearnerTag, Long> {
	public RegisterAndLearnerTag create(@Valid SaveRegisterAndLearnerTagDto dto);

	public RegisterAndLearnerTag save(SaveRegisterAndLearnerTagDto dto, RegisterAndLearnerTag RegisterAndLearnerTag);

	public RegisterAndLearnerTag update(SaveRegisterAndLearnerTagDto dto);

	public boolean checkExistIdofRegisterAndLearnerTag(Long id);

	public void deleteById(Long id);
}