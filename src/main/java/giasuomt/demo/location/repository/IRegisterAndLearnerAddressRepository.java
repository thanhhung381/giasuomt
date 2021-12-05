package giasuomt.demo.location.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import giasuomt.demo.location.model.RegisterAndLearnerAddress;

@Repository
public interface IRegisterAndLearnerAddressRepository extends JpaRepository<RegisterAndLearnerAddress, Long> {

}
