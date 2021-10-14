package giasuomt.demo.task.model;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.location.model.Area;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "task_place_address")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"}) 
public class TaskPlaceAddress extends AbstractEntity {
	
	
	
	private String exactAddNumber;
	
	private String exactAddStreet;
	
	private String exactAddNote;
	
	private String exactXCoo;
	
	private String exactYCoo;
	
	
	private String relAddNumber;
	
	private String relAddStreet;	
	
	private String relAddNote;
	
	private String relXCoo;
	
	private String relYCoo;
	
	@ManyToOne
	@JoinColumn(name = "area_id")
	private Area area;
	
	@ManyToOne
	@JoinColumn(name = "task_id")
	@JsonIgnore
	private Task task;
	
}