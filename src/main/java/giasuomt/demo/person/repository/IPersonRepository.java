package giasuomt.demo.person.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.person.model.Person;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {
	@Query("SELECT a FROM Person a WHERE a.tempArea.nation=:nation AND a.tempArea.provincialLevel=:provincialLevel  AND a.tempArea.commune=:commune AND a.tempArea.district=:district")
	Area findByTempArea(@Param("nation") String nation,@Param("provincialLevel") String provincialLevel,@Param("district") String district,@Param("commune") String commune);
	
	@Query("SELECT a FROM Person a WHERE a.relArea.nation=:nation AND a.relArea.provincialLevel=:provincialLevel  AND a.relArea.commune=:commune AND a.relArea.district=:district")
	Area findByRelArea(@Param("nation") String nation,@Param("provincialLevel") String provincialLevel,@Param("district") String district,@Param("commune") String commune);
	
	@Query("SELECT a FROM Person a WHERE a.perArea.nation=:nation AND a.perArea.provincialLevel=:provincialLevel  AND a.perArea.commune=:commune AND a.perArea.district=:district")
	Area findByPerArea(@Param("nation") String nation,@Param("provincialLevel") String provincialLevel,@Param("district") String district,@Param("commune") String commune);


	//forStudentRepository
	Optional<Person> findByTutorCode(String tutorCode); 
	//
	@Query("SELECT p FROM Person p WHERE p.tutorCode IS NOT NULL AND length(p.tutorCode)=8")
	List<Person> getPersonTutorCodeNotNULL();
	
	@Query("SELECT MAX(id) FROM Person")
	Long getMaxId();
	
	
	
	
	
	
}
