package giasuomt.demo.person.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import giasuomt.demo.commondata.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="person_relationsip")
public class Relationship extends AbstractEntity {
	@ManyToOne
	@JoinColumn(name = "person_a_id")
	private Person personA;
	
	private String relationshipType;
	
	@ManyToOne
	@JoinColumn(name = "person_b_id")
	private Person personB;
}
