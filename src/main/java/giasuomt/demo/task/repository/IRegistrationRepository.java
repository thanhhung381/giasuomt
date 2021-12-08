package giasuomt.demo.task.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import giasuomt.demo.task.model.Registration;

@Repository
public interface IRegistrationRepository extends JpaRepository<Registration, Long> {

}
