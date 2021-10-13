package giasuomt.demo.task.model;
import java.util.HashSet;
import java.util.Set;
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
@Table(name = "require")
public class Require extends AbstractEntity {
	private String name;
	
	private String type;
	
	private String specific;
	
	private String description;
	
//	@ManyToMany(mappedBy = "requires", fetch = FetchType.LAZY)
//	@JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
//	private Set<Task> tasks = new HashSet<>();

}
