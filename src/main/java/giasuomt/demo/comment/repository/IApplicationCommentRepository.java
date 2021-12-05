package giasuomt.demo.comment.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import giasuomt.demo.comment.model.ApplicationComment;

@Repository
public interface IApplicationCommentRepository extends JpaRepository<ApplicationComment, Long> {
	@Transactional
	@Modifying
	@Query("DELETE FROM ApplicationComment a WHERE a.parentComment.id=:id")
	void deleteByParentComment(@Param("id") Long id);
}
