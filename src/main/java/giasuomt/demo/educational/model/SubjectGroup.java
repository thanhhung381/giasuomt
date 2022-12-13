package giasuomt.demo.educational.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import giasuomt.demo.commondata.model.AbstractEntityNotId;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.task.model.Task;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "subject_group")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"}) 
public class SubjectGroup extends AbstractEntityNotId {	
	@Id
	private String id;
	private String name;
	private String shortName;
	@ManyToMany(mappedBy = "subjectGroups",fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Task> tasks=new HashSet<>();
	@ManyToMany(mappedBy = "subjectGroupMaybes",fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Tutor> tutorMaybes=new HashSet<>();
	@ManyToMany(mappedBy = "subjectGroupSures",fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Tutor> tutorSures=new HashSet<>();
	@ManyToMany(mappedBy = "subjectGroupFails",fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Tutor> tutorFails=new HashSet<>();

	
}
