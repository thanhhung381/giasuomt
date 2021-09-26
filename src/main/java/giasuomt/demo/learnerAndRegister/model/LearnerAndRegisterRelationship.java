package giasuomt.demo.learnerAndRegister.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import giasuomt.demo.commondata.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "learner_and_register_relationship")
public class LearnerAndRegisterRelationship extends AbstractEntity {
	@ManyToOne
	@JoinColumn(name = "learnerAndRegister_id")
	@JsonIgnore
	private LearnerAndRegister learnerAndRegister;
	
	private String relationship;
	
	@ManyToOne
	@JoinColumn(name = "relationshipWith__id")
	@JsonIgnore
	private LearnerAndRegister relationshipWith;

    
	//getter,setter kiểu fluentAPI
	public LearnerAndRegisterRelationship learnerAndRegister(LearnerAndRegister learnerAndRegister) {
		this.learnerAndRegister = learnerAndRegister;
		return this;
	}
	public LearnerAndRegisterRelationship relationship(String relationship) {
		this.relationship = relationship;
		return this;
	}
	public LearnerAndRegisterRelationship relationshipWith(LearnerAndRegister relationshipWith) {
		this.relationshipWith = relationshipWith;
		return this;
	}
}
