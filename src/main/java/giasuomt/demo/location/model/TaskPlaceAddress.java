package giasuomt.demo.location.model;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.task.model.Task;
import lombok.Getter;
import lombok.Setter;

/**
 * @author khang le
 *
 */
@Getter
@Setter
@Entity
@Table(name = "task_place_address")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"}) 
public class TaskPlaceAddress extends AbstractEntity {
	private String exactAddNumber;
	private String exactXCoo;
	private String exactYCoo;
	private String relAddNumber;
	private String relXCoo;
	private String relYCoo;
	private String addStreet;
	private String addStreetNote;
	@ManyToOne
	@JoinColumn(name = "area_id")
	private Area area;
	
	@ManyToOne
	@JoinColumn(name = "task_id")
	@JsonIgnore
	private Task task;
	@Override
	public String toString() {
		return  exactAddNumber + ","+area.commune+","+area.district+
				","+area.provincialLevel +"-"
				+  relAddNumber  + ","+area.commune+","+area.district+","+area.provincialLevel ;
	}
}