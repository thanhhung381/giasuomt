package giasuomt.demo.uploadfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import giasuomt.demo.uploadfile.model.Avatar;

@Repository
public interface IAvatarRepository extends JpaRepository<Avatar, Long> {
	Avatar findByNameFile(String name);
	
	
	
	void deleteByNameFile(String name);
	
	
}
