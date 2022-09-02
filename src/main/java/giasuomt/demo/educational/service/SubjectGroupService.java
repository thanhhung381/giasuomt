package giasuomt.demo.educational.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.educational.dto.SaveSubjectGroupDto;
import giasuomt.demo.educational.model.SubjectGroup;
import giasuomt.demo.educational.repository.ISubjectGroupRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubjectGroupService extends GenericService<SaveSubjectGroupDto, SubjectGroup,String>
		implements ISubjectGroupService {
	
	private ISubjectGroupRepository iSubjectGroupRepository;

	private MapDtoToModel mapper;

	@Override
	public SubjectGroup create(SaveSubjectGroupDto dto) {
		SubjectGroup subjectGroup = new SubjectGroup();		
		subjectGroup = (SubjectGroup) mapper.map(dto, subjectGroup);		
		subjectGroup.setId(subjectGroup.getShortName());
		return save(dto, subjectGroup);
	}

	@Override
	public SubjectGroup update(SaveSubjectGroupDto dto) {
		SubjectGroup subjectGroup = iSubjectGroupRepository.getOne(dto.getId());
		subjectGroup = (SubjectGroup) mapper.map(dto, subjectGroup);
		subjectGroup.setId(dto.getShortName());
		return save(dto, subjectGroup);
	}

	@Override
	public Set<SubjectGroup> createAll(Set<SaveSubjectGroupDto> dtos) {
		try {
			Set<SubjectGroup> subjectGroups = new HashSet<>();
			dtos.parallelStream().forEach(dto-> {SubjectGroup subjectGroup = new SubjectGroup();
				subjectGroup = (SubjectGroup) mapper.map(dto, subjectGroup);
				subjectGroups.add(subjectGroup); });
			return Sets.newHashSet(iSubjectGroupRepository.saveAll(subjectGroups)) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
