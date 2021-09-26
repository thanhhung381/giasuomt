package giasuomt.demo.institution.model;

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
import giasuomt.demo.commondata.model.SubjectGroup;
import giasuomt.demo.task.model.Task;

@Entity
@Table(name = "subject")
public class Subject extends AbstractEntity {	
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "subject_group_id")
	private SubjectGroup subjectGroup;
	
	@ManyToMany(mappedBy = "subjects", fetch = FetchType.LAZY)
	@JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
	private Set<Task> tasks = new HashSet<>(); 

	
	//getter,setter
	public SubjectGroup getSubjectGroup() {
		return subjectGroup;
	}

	public void setSubjectGroup(SubjectGroup subjectGroup) {
		this.subjectGroup = subjectGroup;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
