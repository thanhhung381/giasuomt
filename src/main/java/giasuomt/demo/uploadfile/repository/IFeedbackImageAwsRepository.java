package giasuomt.demo.uploadfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.uploadfile.model.FeedbackImageAws;

@Repository
public interface IFeedbackImageAwsRepository extends JpaRepository<FeedbackImageAws, Long> {

	void deleteByUrlFeedbackImage(String urlFeedbackImage);

	int countById(Long id);

}
