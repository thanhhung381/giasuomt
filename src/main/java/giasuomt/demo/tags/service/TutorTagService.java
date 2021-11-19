package giasuomt.demo.tags.service;

import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.tags.dto.SaveTutorTagDto;
import giasuomt.demo.tags.model.TutorTag;
import giasuomt.demo.tags.repository.ITutorTagRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TutorTagService extends GenericService<SaveTutorTagDto, TutorTag, Long> implements ITutorTagService {

	private ITutorTagRepository iTutorTagRepository;

	private MapDtoToModel mapDtoToModel;

	@Override
	public List<TutorTag> findAll() {

		return iTutorTagRepository.findAll();
	}

	public TutorTag create(SaveTutorTagDto dto) {
		TutorTag tutorTag = new TutorTag();

		return save(dto, tutorTag);
	}

	public TutorTag update(SaveTutorTagDto dto) {
		TutorTag tutorTag = iTutorTagRepository.getOne(dto.getId());

		return save(dto, tutorTag);
	}

	public TutorTag save(SaveTutorTagDto dto, TutorTag tutorTag) {
		try {
			tutorTag = (TutorTag) mapDtoToModel.map(dto, tutorTag);

			return iTutorTagRepository.save(tutorTag);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;

	}

	public void deleteById(Long id) {
		try {

			iTutorTagRepository.deleteById(id);
			;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

// check id

	public boolean checkExistIdofTutorTag(Long id) {
		return iTutorTagRepository.countById(id) == 1;
	}

	@Override
	public List<TutorTag> createAll(List<SaveTutorTagDto> dtos) {
		try {
			List<TutorTag> tutorTags = new LinkedList<>();
			for (SaveTutorTagDto dto : dtos) {
				TutorTag tutorTag = new TutorTag();
				tutorTag = (TutorTag) mapDtoToModel.map(dto, tutorTag);
				tutorTags.add(tutorTag);
			}

			return iTutorTagRepository.saveAll(tutorTags);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
