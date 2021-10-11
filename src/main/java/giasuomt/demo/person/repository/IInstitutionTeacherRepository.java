package giasuomt.demo.person.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import giasuomt.demo.person.model.InstitutionTeacher;

@Repository
public interface IInstitutionTeacherRepository extends JpaRepository<InstitutionTeacher, Long> {

}
