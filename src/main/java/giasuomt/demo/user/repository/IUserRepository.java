package giasuomt.demo.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import giasuomt.demo.user.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

	
	
	Optional<User> findByUsername(String us);
	
	public User findByEmail(String email);
	
	
	

	
	@Query("SELECT u.username FROM User u WHERE u.username=:parameter OR u.email=:parameter OR u.phones LIKE CONCAT('%',:parameter,'%')")
	public String findUsernameByParameter(@Param("parameter") String parameter);
	

}
