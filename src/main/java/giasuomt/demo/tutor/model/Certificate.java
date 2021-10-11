package giasuomt.demo.tutor.model;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import giasuomt.demo.commondata.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "certificate")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class Certificate extends AbstractEntity {

	private String certificateName;

	private String certificateType;

	private String description;

	// @ManyToMany(mappedBy = "certificates", fetch = FetchType.LAZY)
	// @JsonIgnore //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
	// private Set<Tutor> tutor = new HashSet<>();
}