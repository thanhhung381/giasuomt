package giasuomt.demo.person.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.person.model.SchoolTeacher;

@Repository
public interface ISchoolTeacherRepository extends JpaRepository<SchoolTeacher, Long> {

}
