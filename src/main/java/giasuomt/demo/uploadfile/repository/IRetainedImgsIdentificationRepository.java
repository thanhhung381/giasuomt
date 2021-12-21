package giasuomt.demo.uploadfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import giasuomt.demo.uploadfile.model.RetainedImgsIdentification;

@Repository
public interface IRetainedImgsIdentificationRepository extends JpaRepository<RetainedImgsIdentification,Long> {

	public RetainedImgsIdentification findByNameFile(String nameFile);
	
}
