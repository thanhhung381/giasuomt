package giasuomt.demo.educational.service;

import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.educational.dto.SaveSchoolDto;
import giasuomt.demo.educational.model.School;
import giasuomt.demo.educational.repository.ISchoolRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SchoolService extends GenericService<SaveSchoolDto, School, Long> implements ISchoolService {

	private MapDtoToModel mapDtoToModel;

	private ISchoolRepository iSchoolRepository;

	public School create(SaveSchoolDto dto) {

		School school = new School();

		school = (School) mapDtoToModel.map(dto, school);

		return save(dto, school);
	}

	@Override
	public School update(SaveSchoolDto dto) {

		School school = iSchoolRepository.getOne(dto.getId());

		school = (School) mapDtoToModel.map(dto, school);

		return save(dto, school);
	}

}
