package giasuomt.demo.educational.model;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import giasuomt.demo.commondata.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "subject_group")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"}) 
public class SubjectGroup extends AbstractEntity {	
	private String name;
	
	private String shortName;
	
	@OneToMany(mappedBy = "subjectGroup", fetch = FetchType.LAZY)
	@JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
	private Set<Subject> subjects;
	
}
