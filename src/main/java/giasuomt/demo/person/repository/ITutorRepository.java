package giasuomt.demo.person.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.person.model.Tutor;

@Repository
public interface ITutorRepository extends JpaRepository<Tutor, Long> {
	@Query("SELECT a FROM Tutor a WHERE a.tempArea.nation=:nation AND a.tempArea.provincialLevel=:provincialLevel  AND a.tempArea.commune=:commune AND a.tempArea.district=:district")
	Area findByTempArea(@Param("nation") String nation, @Param("provincialLevel") String provincialLevel,
			@Param("district") String district, @Param("commune") String commune);

	@Query("SELECT a FROM Tutor a WHERE a.relArea.nation=:nation AND a.relArea.provincialLevel=:provincialLevel  AND a.relArea.commune=:commune AND a.relArea.district=:district")
	Area findByRelArea(@Param("nation") String nation, @Param("provincialLevel") String provincialLevel,
			@Param("district") String district, @Param("commune") String commune);

	@Query("SELECT a FROM Tutor a WHERE a.perArea.nation=:nation AND a.perArea.provincialLevel=:provincialLevel  AND a.perArea.commune=:commune AND a.perArea.district=:district")
	Area findByPerArea(@Param("nation") String nation, @Param("provincialLevel") String provincialLevel,
			@Param("district") String district, @Param("commune") String commune);

	//
	//
	@Query("SELECT p FROM Tutor p   WHERE  p.createdAt=(SELECT MAX(createdAt) FROM Tutor)")  //AND length(p.id)=8
	Tutor getPersonTutorCodeNotNULL();

	@Query("SELECT MAX(id) FROM Tutor")
	Long getMaxId();
	
	
	@Query("SELECT t FROM Tutor t WHERE t.id=:id")
	Tutor findByIdOrTutorCode(Long id);

	int countById(Long id);

	List<Tutor> findByPhonesContaining(String phones);

	int countByPhonesContaining(String phones);
	
	List<Tutor> findByFullNameContaining(String fullName);
	
	@Query("SELECT t.fullName FROM Tutor t WHERE t.englishFullName LIKE CONCAT('%',:englishFullName,'%')")
	List<String> findByEnglishNameAndShowFullName(@Param("englishFullName") String englishFullName); 
	
	@Query("SELECT t.fullName FROM Tutor t WHERE t.fullName LIKE CONCAT('%',:fullName,'%')")
	List<String>  showFullname(@Param("fullName") String fullName);
	
	int countByFullNameContaining(String fullName);
	
	
	List<Tutor> findByEnglishFullNameContaining(String englishFullName);
	
	@Query("SELECT t FROM Tutor t WHERE t.averageStarNumbers >= 4.5")
	List<Tutor> findByAverageStarNumbersGreaterThanEquals();
	
	
	

	
}
