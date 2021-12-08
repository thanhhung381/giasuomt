package giasuomt.demo.educational.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.scheduling.config.ScheduledTaskHolder;
import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.commondata.generic.StringUltilsForAreaID;
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
		
		school.setName(dto.getName().toUpperCase());
		school.setEnglishName(StringUltilsForAreaID.removeAccent(dto.getName()).toUpperCase());

		return save(dto, school);
	}

	@Override
	public School update(SaveSchoolDto dto) {

		School school = iSchoolRepository.getOne(dto.getId());

		school = (School) mapDtoToModel.map(dto, school);
		
		school.setName(dto.getName().toUpperCase());
		school.setEnglishName(StringUltilsForAreaID.removeAccent(dto.getName()).toUpperCase());

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

	@Override
	public List<School> findByNameContaining(String name) {
		
		return iSchoolRepository.findByNameContaining(name);
	}

	@Override
	public List<School> findByEnglishNameContaining(String englishName) {

		return iSchoolRepository.findByEnglishNameContaining(englishName);
	}

	@Override
	public List<School> findByDistrictAndNameContaining(String district, String name) {

		return iSchoolRepository.findByDistrictAndNameContaining(district, name);
	}

	@Override
	public List<School> findByDistrictAndEnglishNameContaining(String district, String englishName) {

		return iSchoolRepository.findByDistrictAndEnglishNameContaining(district, englishName);
	}

	@Override
	public List<School> findBySchoolTypesAndNameContaining(String schoolTypes, String name) {

		return iSchoolRepository.findBySchoolTypesAndNameContaining(schoolTypes, name);
	}

	@Override
	public List<School> findBySchoolTypesAndEnglishNameContaining(String schoolTypes, String englishName) {
		return iSchoolRepository.findBySchoolTypesAndEnglishNameContaining(schoolTypes, englishName);
	}

	@Override
	public List<School> findBySchoolTypesAndDistrictAndNameContaining(String schoolTypes, String district,
			String name) {
		
		return iSchoolRepository.findBySchoolTypesAndDistrictAndNameContaining(schoolTypes, district, name);
	}

	@Override
	public List<School> findBySchoolTypesAndDistrictAndEnglishNameContaining(String schoolTypes, String district,
			String englishName) {
	
		return iSchoolRepository.findBySchoolTypesAndDistrictAndEnglishNameContaining(schoolTypes, district, englishName);
	}

}
