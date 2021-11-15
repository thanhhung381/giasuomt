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
@Table(name = "graduated_student")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class GraduatedStudent extends AbstractEntity {
	private String diplomaImgs;

	private String graduatedYear;

	String institutionName;

	String institutionAbbrName;

	String institutionCode;

	String institutionType;

	String majorName;

	String majorCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tutor_id")
	@JsonIgnore
	private Tutor tutor;
}