package giasuomt.demo.uploadfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import giasuomt.demo.uploadfile.model.AvatarAws;

@Repository
public interface IAvatarAwsRepository extends JpaRepository<AvatarAws, Long> {

	

	void deleteByUrlAvatar(String urlAvatar);
	
	int countById(Long id);
	

	
}
