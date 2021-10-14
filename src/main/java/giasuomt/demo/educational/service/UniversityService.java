package giasuomt.demo.educational.service;

import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.educational.dto.SaveUniversityDto;
import giasuomt.demo.educational.model.University;
import giasuomt.demo.educational.repository.IUniversityRepository;
import giasuomt.demo.person.repository.IRelationshipRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UniversityService extends GenericService<SaveUniversityDto, University, Long>
		implements IUniversityService {
	

	private IUniversityRepository iUniversityRepository;

	private MapDtoToModel mapDtoToModel;

	public University create(SaveUniversityDto dto) {

		University university = new University();
		university = (University) mapDtoToModel.map(dto, university);

		return save(dto, university);
	}

	@Override
	public University update(SaveUniversityDto dto) {

		University university = iUniversityRepository.getOne(dto.getId());
		university = (University) mapDtoToModel.map(dto, university);

		return save(dto, university);
	}

}
