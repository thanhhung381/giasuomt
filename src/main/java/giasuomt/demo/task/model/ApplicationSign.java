package giasuomt.demo.task.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.task.util.Sign;

@Entity
@Table(name = "application_sign")
public class ApplicationSign extends AbstractEntity {
	private Sign sign;
	
	@ManyToOne
	@JoinColumn(name = "application_id")
	private Application application;

	//getter,setter
	public Sign getSign() {
		return sign;
	}

	public void setSign(Sign sign) {
		this.sign = sign;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	
}
