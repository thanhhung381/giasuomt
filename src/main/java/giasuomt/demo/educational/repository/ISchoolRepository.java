package giasuomt.demo.educational.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.educational.model.School;

@Repository
public interface ISchoolRepository extends JpaRepository<School, Long> {
	
	List<School> findByNameContaining(String name);
	
	List<School> findByEnglishNameContaining(String englishName);
	
	List<School> findByDistrictAndNameContaining(String district,String name);
	
	List<School> findByDistrictAndEnglishNameContaining(String district,String englishName);
	
	List<School> findBySchoolTypesAndNameContaining(String schoolTypes,String name);
	
	List<School> findBySchoolTypesAndEnglishNameContaining(String schoolTypes,String englishName);
	
	List<School> findBySchoolTypesAndDistrictAndNameContaining(String schoolTypes,String district,String name);

	List<School> findBySchoolTypesAndDistrictAndEnglishNameContaining(String schoolTypes,String district,String englishName);

}
