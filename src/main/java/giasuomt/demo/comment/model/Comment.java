package giasuomt.demo.comment.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.commondata.model.AbstractEntityNotId;
import giasuomt.demo.task.model.Application;
import giasuomt.demo.task.model.Task;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@MappedSuperclass // để các lớp con có thể kế thừa lại được các annotation của lớp này
@EntityListeners(AuditingEntityListener.class) // để model này lấy được @EnableJpaAuditing đã Config ở bên // JpaConfig.java để @CreatedDate,@LastModifiedDate có hiệu lực
public class Comment extends AbstractEntity {
	
	private String content;
	
//	@OneToMany(mappedBy = "comment", fetch = FetchType.EAGER)
//	private Set<CommentEmotion> commentEmotions;

	
    
    
}
