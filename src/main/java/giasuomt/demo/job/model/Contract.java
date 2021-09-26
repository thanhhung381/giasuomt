package giasuomt.demo.job.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import giasuomt.demo.commondata.model.AbstractEntity;

@Entity
@Table(name = "contract")
public class Contract extends AbstractEntity {
	@OneToOne(mappedBy = "contract")
	private Job job;
}
