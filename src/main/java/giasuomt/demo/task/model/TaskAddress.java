package giasuomt.demo.task.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import giasuomt.demo.location.model.Address;
import giasuomt.demo.location.model.Area;

@Entity
@Table(name = "task_address")
public class TaskAddress extends Address{
	@ManyToOne
	@JoinColumn(name = "task_id")
	private Task task;
	
	@ManyToOne
	@JoinColumn(name = "area_id")
	private Area area;
	
	
	
	//getter,setter
	
	public Task getTask() {
		return task;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
}
