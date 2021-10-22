package giasuomt.demo.uploadfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import giasuomt.demo.uploadfile.model.FileEntity;

@Repository
public interface IFileEntityRepository extends JpaRepository<FileEntity, Long> {

}
