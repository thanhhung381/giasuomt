package giasuomt.demo.educational.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.educational.model.University;

@Repository
public interface IUniversityRepository extends JpaRepository<University, Long> {
			
	List<University> findByNameContaining(String name);
	
	List<University> findByEnglishNameContaining(String englishName);
	
	
	List<University> findByCode(String code);
	
	List<University> findByNameAbbrContaining(String nameAbbr);
	
	List<University> findByUniversityTypesContainingAndNameContaining(String universityTypes,String name);
	
	List<University> findByUniversityTypesContainingAndEnglishNameContaining(String universityTypes,String name);
	
	
}
