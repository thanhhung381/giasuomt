package giasuomt.demo.uploadfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.uploadfile.model.BillIamgeAws;

@Repository
public interface IBillImageAwsRepository extends JpaRepository<BillIamgeAws, Long> {

	void deleteByUrlBillImage(String urlBillImage);

	int countById(Long id);

}
