package giasuomt.demo.location.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.location.model.Area;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {

	List<Area> findByNationAndProvincialLevelAndDistrictAndCommune(String nation, String provincialLevel, String district, String commune);


	Optional<Area> findById(Long areaId);
}
