package giasuomt.demo.tutorReview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import giasuomt.demo.tutorReview.model.TutorReview;

@Repository
public interface ITutorReviewRepository extends JpaRepository<TutorReview, Long> {
	
	@Query("SELECT tr.starNumber FROM TutorReview tr WHERE tr.tutor.id=:id")
	List<Double> findAllByIdTutor(@Param("id") Long id); 
	
	
}
