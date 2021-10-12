package giasuomt.demo.person.model;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import giasuomt.demo.commondata.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "certificate")
public class Certificate extends AbstractEntity {

	private String certificateName;

	private String certificateType;

	private String description;

	@ManyToMany(mappedBy = "certificates", fetch = FetchType.LAZY)
	@JsonIgnore 
	private List<Person> person = new ArrayList<>();
}