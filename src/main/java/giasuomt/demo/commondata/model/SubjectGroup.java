package giasuomt.demo.commondata.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import giasuomt.demo.institution.model.Subject;
import giasuomt.demo.task.model.Task;

@Entity
@Table(name = "subject_group")
public class SubjectGroup extends AbstractEntity {	
	private String name;
	
	private String shortName;
	
	@OneToMany(mappedBy = "subjectGroup", fetch = FetchType.LAZY)
	@JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
	private Set<Subject> subjects;
	
	@ManyToMany(mappedBy = "subjectGroups", fetch = FetchType.LAZY)
	@JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
	private Set<Task> tasks = new HashSet<>(); 

	
	//getter,setter
	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}
	
	
	
}
