package giasuomt.demo.uploadfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.uploadfile.model.RetainedImgsIdentificationAws;

@Repository
public interface IRetainedImgsIdentificationAwsRepository extends JpaRepository<RetainedImgsIdentificationAws, Long> {

	void deleteByUrlRetainedImgsIdentification(String urlRetainedImgsIdentification);

	int countById(Long id);

}
