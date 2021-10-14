package giasuomt.demo.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.task.model.Require;

@Repository
public interface IRequireRepository extends JpaRepository<Require, Long> {

}
