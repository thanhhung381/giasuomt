package giasuomt.demo.person.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import giasuomt.demo.commondata.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "person_relationsip")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class Relationship extends AbstractEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_a_id")
	@JsonIgnore
	private RegisterAndLearner personA;

	private String relationshipType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_b_id")
	private RegisterAndLearner personB;

}
