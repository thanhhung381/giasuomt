package giasuomt.demo.tags.service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.tags.dto.SaveRegisterAndLearnerTagDto;
import giasuomt.demo.tags.model.RegisterAndLearnerTag;
import giasuomt.demo.tags.repository.IRegisterAndLearnerTagRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegisterAndLearnerTagService
		extends GenericService<SaveRegisterAndLearnerTagDto, RegisterAndLearnerTag, Long>
		implements IRegisterAndLearnerTagService {

	private IRegisterAndLearnerTagRepository iRegisterAndLearnerTagRepository;

	private MapDtoToModel mapDtoToModel;

	@Override
	public List<RegisterAndLearnerTag> findAll() {
		return iRegisterAndLearnerTagRepository.findAll();
	}

	public RegisterAndLearnerTag create(SaveRegisterAndLearnerTagDto dto) {
		RegisterAndLearnerTag registerAndLearnerTag = new RegisterAndLearnerTag();
		return save(dto, registerAndLearnerTag);
	}

	public RegisterAndLearnerTag update(SaveRegisterAndLearnerTagDto dto) {
		RegisterAndLearnerTag registerAndLearnerTag = iRegisterAndLearnerTagRepository.getOne(dto.getId());
		return save(dto, registerAndLearnerTag);
	}

	public RegisterAndLearnerTag save(SaveRegisterAndLearnerTagDto dto, RegisterAndLearnerTag registerAndLearnerTag) {
		try {
			registerAndLearnerTag = (RegisterAndLearnerTag) mapDtoToModel.map(dto, registerAndLearnerTag);
			return iRegisterAndLearnerTagRepository.save(registerAndLearnerTag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public void deleteById(Long id) {
		try {
			iRegisterAndLearnerTagRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

// check id

	public boolean checkExistIdofRegisterAndLearnerTag(Long id) {
		return iRegisterAndLearnerTagRepository.countById(id) == 1;
	}

	@Override
	public Set<RegisterAndLearnerTag> createAll(Set<SaveRegisterAndLearnerTagDto> dtos) {
		try {
			Set<RegisterAndLearnerTag> registerAndLearnerTags = new HashSet<>();
			for (SaveRegisterAndLearnerTagDto dto : dtos) {
				RegisterAndLearnerTag registerAndLearnerTag = new RegisterAndLearnerTag();
				registerAndLearnerTag = (RegisterAndLearnerTag) mapDtoToModel.map(dto, registerAndLearnerTag);
				registerAndLearnerTags.add(registerAndLearnerTag);
			}
			return Sets.newHashSet(iRegisterAndLearnerTagRepository.saveAll(registerAndLearnerTags)) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
