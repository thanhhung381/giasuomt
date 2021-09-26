package giasuomt.demo.task.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.task.util.Sign;

@Entity
@Table(name = "task_sign")
public class TaskSign extends AbstractEntity {
	private Sign sign;
	
	@ManyToOne
	@JoinColumn(name = "task_id")
	private Task task;

	
	//getter,setter
	public Sign getSign() {
		return sign;
	}

	public void setSign(Sign sign) {
		this.sign = sign;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
	
}
