package giasuomt.demo.educational.service;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.stereotype.Service;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.educational.dto.SaveSubjectGroupDto;
import giasuomt.demo.educational.model.SubjectGroup;
import giasuomt.demo.educational.repository.ISubjectGroupRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubjectGroupService extends GenericService<SaveSubjectGroupDto, SubjectGroup, Long>
		implements ISubjectGroupService {
	private ISubjectGroupRepository iSubjectGroupRepository;

	private MapDtoToModel mapper;

	@Override
	public SubjectGroup create(@Valid SaveSubjectGroupDto dto) {
		SubjectGroup subjectGroup = new SubjectGroup();

		subjectGroup = (SubjectGroup) mapper.map(dto, subjectGroup);

		return save(dto, subjectGroup);
	}

	@Override
	public SubjectGroup update(SaveSubjectGroupDto dto) {
		SubjectGroup subjectGroup = iSubjectGroupRepository.getOne(dto.getId());

		subjectGroup = (SubjectGroup) mapper.map(dto, subjectGroup);

		return save(dto, subjectGroup);
	}

	@Override
	public List<SubjectGroup> createAll(List<SaveSubjectGroupDto> dtos) {
		try {

			List<SubjectGroup> subjectGroups = new LinkedList<>();
			for (SaveSubjectGroupDto dto : dtos) {
				SubjectGroup subjectGroup = new SubjectGroup();

				subjectGroup = (SubjectGroup) mapper.map(dto, subjectGroup);
				subjectGroups.add(subjectGroup);
			}

			return iSubjectGroupRepository.saveAll(subjectGroups);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
