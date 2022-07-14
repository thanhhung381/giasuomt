package giasuomt.demo.tags.model;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import giasuomt.demo.person.model.RegisterAndLearner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "register_and_learner_tag")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class RegisterAndLearnerTag extends Tag {
	
	
	@Id //Quy định khoá chính
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Sinh ra các giá trị là độc lập cho các model khác nhau
	@Column(updatable = false) //Column này ko update được
	protected Long id; //(khai báo là protected để các lớp con có thể truy cập vô để kế thừa)
	
	@ManyToMany(mappedBy = "registerAndLearnerTags", fetch = FetchType.LAZY)
	@JsonIgnore 
	private Set<RegisterAndLearner> registerAndLearner = new HashSet<>();
}
