package giasuomt.demo.educational.service;

import java.util.List;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.educational.dto.SaveUniversityDto;
import giasuomt.demo.educational.model.University;

public interface IUniversityService extends IGenericService<SaveUniversityDto, University, Long> {
	List<University> findByNameContaining(String name);
	
	List<University> findByEnglishNameContaining(String englishName);
	
	
	List<University> findByCode(String code);
	
	List<University> findByNameAbbr(String nameAbbr);
	
	List<University> findByUniversityTypesContainingAndNameContaining(String universityTypes,String name);
	
	List<University> findByUniversityTypesContainingAndEnglishNameContaining(String universityTypes,String name);
}
