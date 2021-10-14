package giasuomt.demo.educational.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface IUniversityRepository {
	
	int countById(Long id);
	
}
