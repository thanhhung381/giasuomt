package giasuomt.demo.educational.model;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.task.model.Task;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "subject_group")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"}) 
public class SubjectGroup extends AbstractEntity {	
	
	private String name;
	
	private String shortName;
	
	@ManyToMany(mappedBy = "subjectGroups",fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Task> tasks=new LinkedList<>();
	
	@ManyToMany(mappedBy = "subjectGroupMaybes",fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Tutor> tutorMaybes=new LinkedList<>();

	@ManyToMany(mappedBy = "subjectGroupSures",fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Tutor> tutorSures=new LinkedList<>();

	
}
