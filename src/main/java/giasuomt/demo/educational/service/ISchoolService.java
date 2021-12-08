package giasuomt.demo.educational.service;

import java.util.List;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.educational.dto.SaveSchoolDto;
import giasuomt.demo.educational.model.School;
import giasuomt.demo.person.model.Schooler;

public interface ISchoolService extends IGenericService<SaveSchoolDto, School, Long> {

	List<School> findByNameContaining(String name);

	List<School> findByEnglishNameContaining(String englishName);

	List<School> findByDistrictAndNameContaining(String district, String name);

	List<School> findByDistrictAndEnglishNameContaining(String district, String englishName);

	List<School> findBySchoolTypesAndNameContaining(String schoolTypes, String name);

	List<School> findBySchoolTypesAndEnglishNameContaining(String schoolTypes, String englishName);

	List<School> findBySchoolTypesAndDistrictAndNameContaining(String schoolTypes, String district, String name);

	List<School> findBySchoolTypesAndDistrictAndEnglishNameContaining(String schoolTypes, String district,
			String englishName);

}
