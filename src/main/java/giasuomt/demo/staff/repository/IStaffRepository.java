package giasuomt.demo.staff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.staff.model.Staff;

@Repository
public interface IStaffRepository extends JpaRepository<Staff, Long> {

}
