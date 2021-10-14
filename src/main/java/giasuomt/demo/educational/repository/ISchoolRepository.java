package giasuomt.demo.educational.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.educational.model.School;

@Repository
public interface ISchoolRepository extends JpaRepository<School, Long> {

}
