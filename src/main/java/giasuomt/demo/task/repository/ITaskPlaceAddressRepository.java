package giasuomt.demo.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.task.model.TaskPlaceAddress;

@Repository
public interface ITaskPlaceAddressRepository extends JpaRepository<TaskPlaceAddress, Long> {

}
