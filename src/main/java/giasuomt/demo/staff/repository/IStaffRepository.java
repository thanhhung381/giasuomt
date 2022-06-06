package giasuomt.demo.staff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import giasuomt.demo.staff.model.Staff;

@Repository
public interface IStaffRepository extends JpaRepository<Staff, Long> {

	@Query("SELECT s FROM Staff s WHERE s.avatar IS NOT NULL")
	List<Staff> findAllStaffSynchronized();

}
