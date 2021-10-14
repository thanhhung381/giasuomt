package giasuomt.demo.educational.service;

import java.util.List;

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
public class SubjectService  implements ISubjectService {

	private ISubjectGroupRepository iSubjectGroupRepository;

	private ISubjectRepository iSubjectRepository;

	private MapDtoToModel mapDtoToModel;

	public List<Subject> findall() {

		return iSubjectRepository.findAll();
	}

	
	public Subject create(SaveSubjectDto dto) {

		Subject subject = new Subject();

		return save(dto, subject);
	}

	@Override
	public Subject save(SaveSubjectDto dto, Subject subject) {

		try {
			subject = (Subject) mapDtoToModel.map(dto, subject);

			subject.setSubjectGroup(iSubjectGroupRepository.getOne(dto.getIdSubjectGroup()));

			return iSubjectRepository.save(subject);

		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public void delete(Long id) {
		try {

			iSubjectRepository.deleteById(id);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean checkExistIdofSubject(Long id) {

		return iSubjectRepository.countById(id) == 1;
	}

	@Override
	public Subject update(SaveSubjectDto dto) {
		Subject subject = iSubjectRepository.getOne(dto.getId());

		return save(dto, subject);
	}

}
