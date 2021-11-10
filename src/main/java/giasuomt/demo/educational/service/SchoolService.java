package giasuomt.demo.educational.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.scheduling.config.ScheduledTaskHolder;
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

	@Override
	public List<School> createAll(List<SaveSchoolDto> dtos) {

		try {
			List<School> schools = new LinkedList<>();
			for (SaveSchoolDto dto : dtos) {
				School school = new School();

				school = (School) mapDtoToModel.map(dto, school);
				schools.add(school);
			}

			return iSchoolRepository.saveAll(schools);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
