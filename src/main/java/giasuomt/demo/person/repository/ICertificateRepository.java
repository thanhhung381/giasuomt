package giasuomt.demo.person.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import giasuomt.demo.person.model.Certificate;

@Repository
public interface ICertificateRepository extends JpaRepository<Certificate, Long> {
	int countById(Long id);
}
