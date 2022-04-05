package giasuomt.demo.role.model;

import java.util.LinkedList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import giasuomt.demo.commondata.model.AbstractEntity;

import giasuomt.demo.job.model.Job;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.person.model.GraduatedStudent;
import giasuomt.demo.person.model.InstitutionTeacher;
import giasuomt.demo.person.model.SchoolTeacher;
import giasuomt.demo.person.model.Student;
import giasuomt.demo.person.model.Worker;
import giasuomt.demo.tags.model.TutorTag;
import giasuomt.demo.task.model.Application;
import giasuomt.demo.user.model.User;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "role")
@Getter
@Setter
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class Role extends AbstractEntity {
	
	private String name;

	private String description;
	
	@ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
	@JsonIgnore
	private List<User> users=new LinkedList<>();
	
	public Role addRoleName(String name)
	{
		this.name=name;
		return this;
	}
}
