package giasuomt.demo.job.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import giasuomt.demo.commondata.model.AbstractEntity;

@Entity
@Table(name = "referral")
public class Referral extends AbstractEntity {
	@OneToOne(mappedBy = "referral")
	private Job job;
}
