package giasuomt.demo.location.model;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.person.model.RegisterAndLearner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "register_and_learner_address")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"}) 
public class RegisterAndLearnerAddress extends AbstractEntity {
	private String addType;
	private String addNumber;
	private String addStreet;
	private String addNote;
	private String xCoo;
	private String yCoo;
	@ManyToOne
	@JoinColumn(name = "area_id")
	private Area area;
	@ManyToOne
	@JoinColumn(name = "register_and_learner_id")
	@JsonIgnore
	private RegisterAndLearner registerAndLearner;
}
