package giasuomt.demo.educational.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.educational.model.University;

@Repository
public interface IUniversityRepository extends JpaRepository<University, Long> {

}
