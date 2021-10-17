package giasuomt.demo.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.task.model.Application;

@Repository
public interface IApplicationRepository extends JpaRepository<Application, Long> {

}
