package giasuomt.demo.educational.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.educational.model.Major;

@Repository
public interface IMajorRepository extends JpaRepository<Major, Long> {

}
