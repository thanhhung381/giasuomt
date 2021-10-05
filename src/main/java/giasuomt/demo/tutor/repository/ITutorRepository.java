package giasuomt.demo.tutor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import giasuomt.demo.tutor.model.Tutor;

@Repository
public interface ITutorRepository extends JpaRepository<Tutor, Long> {


}
