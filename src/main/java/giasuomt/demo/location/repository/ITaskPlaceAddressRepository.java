package giasuomt.demo.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.location.model.TaskPlaceAddress;

@Repository
public interface ITaskPlaceAddressRepository extends JpaRepository<TaskPlaceAddress, Long> {

}
