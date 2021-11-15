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
@Table(name = "institution_teacher")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class InstitutionTeacher extends AbstractEntity {
	private String confirmImgs;

	private String institutionName;

	private String institutionAbbrName;

	private String institutionType;

	private String subject;

	@ManyToOne
	@JoinColumn(name = "tutor_id")
	@JsonIgnore
	private Tutor tutor;

}