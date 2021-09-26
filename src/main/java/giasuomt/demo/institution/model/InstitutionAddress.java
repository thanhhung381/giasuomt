package giasuomt.demo.institution.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import giasuomt.demo.location.model.Address;

@Entity
@Table(name = "institution_address")
public class InstitutionAddress extends Address {
	@ManyToOne
	@JoinColumn(name="university_id")
	private Institution institution;
}
