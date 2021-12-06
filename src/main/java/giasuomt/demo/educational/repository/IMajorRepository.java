package giasuomt.demo.educational.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import giasuomt.demo.educational.model.Major;

@Repository
public interface IMajorRepository extends JpaRepository<Major, Long> {
	
	
	
	List<Major> findByCode(String code);
	
	List<Major> findByNameContaining(String name);
	
	List<Major> findByEnglishNameContaining(String englishName);
	
	@Query("SELECT m FROM Major m WHERE m.university.id=:id AND m.code=:code")
	List<Major> findByUniversityIdAndCode(@Param("id") Long id,@Param("code") String code);
	
	
	@Query("SELECT m FROM Major m WHERE m.university.id=:id AND m.name=:name")
	List<Major> findByUniversityIdAndName(@Param("id") Long id,@Param("name") String name);
	
	@Query("SELECT m FROM Major m WHERE m.university.id=:id AND m.englishName=:englishName")
	List<Major> findByUniversityIdAndEnglishName(@Param("id") Long id,@Param("englishName") String englishName);
	
	
	
	
	
}
