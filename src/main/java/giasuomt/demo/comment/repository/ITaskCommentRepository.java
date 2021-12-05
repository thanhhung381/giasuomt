package giasuomt.demo.comment.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import giasuomt.demo.comment.model.TaskComment;

@Repository
public interface ITaskCommentRepository extends JpaRepository<TaskComment, Long> {
	
	@Transactional
	@Modifying
	@Query("DELETE FROM TaskComment t  WHERE t.parentComment.id=:id")
	void deleteByParrentComment(@Param("id") Long id);
}
