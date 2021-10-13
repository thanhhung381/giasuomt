package giasuomt.demo.educational.model;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.task.model.Task;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "subject")
public class Subject extends AbstractEntity {
	private String name;
	
	private String type;
	
	private String subject;
	
	private String level;
	
	@ManyToOne
	@JoinColumn(name = "subject_group_id")
	private SubjectGroup subjectGroup;
	
//	@ManyToMany(mappedBy = "subjects", fetch = FetchType.LAZY)
//	@JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
//	private Set<Task> tasks = new HashSet<>(); 

}
