package giasuomt.demo.user.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import giasuomt.demo.user.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

	
	@EntityGraph(value = "user-load")
	Optional<User> findByUsername(String us);
	
//	OR (u.staff IS NOT NULL AND ((u.staff.emails=:parameter) OR (u.staff.phones LIKE CONCAT('%',:parameter,'%'))) ) public User findByEmail(String email);  OR (u.registerAndLearner IS NOT NULL AND ( u.registerAndLearner.emails=:parameter OR u.registerAndLearner.phones LIKE CONCAT('%',:parameter,'%') ) )
	
	@Query("SELECT u FROM User u WHERE  (u.tutor IS NOT NULL AND  (u.tutor.emails=:parameter)) OR "
			+ "(u.registerAndLearner IS NOT NULL AND (u.registerAndLearner.emails=:parameter)) OR"
			+ "(u.staff IS NOT NULL AND (u.staff.emails=:parameter)) " )
	User findByEmails(@Param("parameter") String parameter);
	

	
	@Query("SELECT u.username FROM User u WHERE u.username=:parameter  ")
	public String findUsernameByParameter(@Param("parameter") String parameter);
	
	
	
	

}
