package giasuomt.demo.tutorrequest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.tutorrequest.model.TutorRequest;

@Repository
public interface ITutorRequestRepository extends JpaRepository<TutorRequest, Long> {

}
