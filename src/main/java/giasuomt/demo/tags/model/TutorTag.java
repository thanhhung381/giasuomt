package giasuomt.demo.tags.model;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@Table(name = "tutor_tag")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class TutorTag extends Tag {

	@Id
	private String id;
	
	
	@ManyToMany(mappedBy = "tutorTags", fetch = FetchType.LAZY)
	@JsonIgnore 
	private Set<Tutor> tutor = new HashSet<>();

	@Override
	public String toString() {
		return  tagType + "-" + tagGroup + "-" + tagName;
	}

	
	
	
}
