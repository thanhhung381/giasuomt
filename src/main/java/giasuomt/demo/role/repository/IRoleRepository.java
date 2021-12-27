package giasuomt.demo.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.role.model.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

}
