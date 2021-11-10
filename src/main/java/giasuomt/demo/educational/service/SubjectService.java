package giasuomt.demo.educational.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.educational.dto.SaveMajorDto;
import giasuomt.demo.educational.dto.SaveSubjectDto;
import giasuomt.demo.educational.model.Major;
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

	@Override
	public List<Subject> createAll(List<SaveSubjectDto> dtos) {

		try {
			List<Subject> subjects = new LinkedList<>();
			for (SaveSubjectDto dto : dtos) {
				Subject subject = new Subject();

				subject = (Subject) mapDtoToModel.map(dto, subject);

				subject.setSubjectGroup(iSubjectGroupRepository.getOne(dto.getIdSubjectGroup()));

				subjects.add(subject);
			}

			return iSubjectRepository.saveAll(subjects);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
