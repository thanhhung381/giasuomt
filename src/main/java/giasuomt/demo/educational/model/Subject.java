package giasuomt.demo.educational.model;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.task.model.Task;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "subject")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"}) 
public class Subject extends AbstractEntity {
	private String name;
	
	private String type;
	
	private String subject;
	
	private String level;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "subject_group_id")
	private SubjectGroup subjectGroup;
	
	@ManyToMany(mappedBy = "subjects", fetch = FetchType.EAGER)  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
	@JsonIgnore
	private List<Task> tasks = new LinkedList<>(); //truyền id;

}
