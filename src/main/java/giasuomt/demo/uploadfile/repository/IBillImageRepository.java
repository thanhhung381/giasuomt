package giasuomt.demo.uploadfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.uploadfile.model.BillImage;

@Repository
public interface IBillImageRepository extends JpaRepository<BillImage, Long> {
	
	
	BillImage findByNameFile(String nameFile);
	
}
