package giasuomt.demo.tutor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import giasuomt.demo.location.model.Area;
import giasuomt.demo.tutor.model.Tutor;
@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {
	@Query("SELECT a FROM Tutor a WHERE a.tempArea.nation=:nation AND a.tempArea.provincialLevel=:provincialLevel  AND a.tempArea.commune=:commune AND a.tempArea.district=:district")
	Area findByTempArea(@Param("nation") String nation,@Param("provincialLevel") String provincialLevel,@Param("district") String district,@Param("commune") String commune);
	
	@Query("SELECT a FROM Tutor a WHERE a.relArea.nation=:nation AND a.relArea.provincialLevel=:provincialLevel  AND a.relArea.commune=:commune AND a.relArea.district=:district")
	Area findByRelArea(@Param("nation") String nation,@Param("provincialLevel") String provincialLevel,@Param("district") String district,@Param("commune") String commune);
	
	@Query("SELECT a FROM Tutor a WHERE a.perArea.nation=:nation AND a.perArea.provincialLevel=:provincialLevel  AND a.perArea.commune=:commune AND a.perArea.district=:district")
	Area findByPerArea(@Param("nation") String nation,@Param("provincialLevel") String provincialLevel,@Param("district") String district,@Param("commune") String commune);


	//forStudentRepository
	Optional<Tutor> findByTutorCode(String tutorCode); 
}
