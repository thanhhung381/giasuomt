package giasuomt.demo.educational.service;

import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.educational.dto.SaveSubjectDto;
import giasuomt.demo.educational.model.Subject;
import giasuomt.demo.educational.repository.ISubjectGroupRepository;
import giasuomt.demo.educational.repository.ISubjectRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubjectService extends GenericService<SaveSubjectDto, Subject, Long> implements ISubjectService {

	private ISubjectRepository iSubjectRepository;

	private ISubjectGroupRepository iSubjectGroupRepository;

	private MapDtoToModel mapDtoToModel;

	public Subject create(SaveSubjectDto dto) {
		Subject subject = new Subject();

		subject = (Subject) mapDtoToModel.map(dto, subject);

		subject.setSubjectGroup(iSubjectGroupRepository.getOne(dto.getIdSubjectGroup()));

		return save(dto, subject);
	}

	@Override
	public Subject update(SaveSubjectDto dto) {

		Subject subject = iSubjectRepository.getOne(dto.getId());

		subject = (Subject) mapDtoToModel.map(dto, subject);

		subject.setSubjectGroup(iSubjectGroupRepository.getOne(dto.getIdSubjectGroup()));

		return save(dto, subject);
	}

}
