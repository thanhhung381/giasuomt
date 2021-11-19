package giasuomt.demo.tags.model;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import giasuomt.demo.person.model.Tutor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tutor-tag")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class TutorTag extends Tag {
	@ManyToMany(mappedBy = "tutorTags", fetch = FetchType.LAZY)
	@JsonIgnore 
	private List<Tutor> tutor = new LinkedList<>();
}
