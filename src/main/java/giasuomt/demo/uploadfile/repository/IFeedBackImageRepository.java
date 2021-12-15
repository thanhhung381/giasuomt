package giasuomt.demo.uploadfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.uploadfile.model.FeedBackImage;

@Repository
public interface IFeedBackImageRepository extends JpaRepository<FeedBackImage, Long> {
	
	
	FeedBackImage findByNameFile(String nameFile);
	
}
