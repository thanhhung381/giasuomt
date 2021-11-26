package giasuomt.demo.location.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import giasuomt.demo.location.model.Area;

@Repository
public interface IAreaRepository extends JpaRepository<Area, String> {
	
	@Query("SELECT a FROM Area a WHERE a.nation=:nation AND a.provincialLevel=:provincialLevel AND a.district=:district AND a.commune=:commune")
	List<Area> findByNationAndProvincialLevelAndDistrictAndCommune(@Param("nation") String nation,@Param("provincialLevel") String provincialLevel
			,@Param("district") String district,@Param("commune") String commune);
	
	@Query("SELECT a FROM Area a WHERE a.nation=:nation AND a.provincialLevel=:provincialLevel AND a.district=:district")
	List<Area> findByNationAndProvincialLevelAndStateAndDistrict(@Param("nation") String nation,@Param("provincialLevel") String provincialLevel, @Param("district") String district);
	
	@Query("SELECT a FROM Area a WHERE a.nation=:nation AND a.provincialLevel=:provincialLevel")
	List<Area> findByNationAndProvincialLevelAndState(@Param("nation") String nation,@Param("provincialLevel") String provincialLevel);
	
	
	@Query("SELECT a FROM Area a WHERE a.nation=:nation")
	List<Area> findByNation(@Param("nation") String nation);
	
	@Query("SELECT a FROM Area a WHERE a.nation=:nation AND a.state=:state")
	List<Area> findByNationAndState(@Param("nation") String nation,@Param("state") String state);
	
	@Query("SELECT a.id FROM Area a WHERE a.id=:id ")
	Area findByIdString(String id);
	

	

	
	
	
	int countById(Long id);
	
	int countByDistrict(String district);
	int countByProvincialLevel(String provincialLevel);
	int countByCommune(String commune);
	
	
	Optional<Area> findById(Long areaId);
}
