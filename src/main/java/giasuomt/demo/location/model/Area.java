package giasuomt.demo.location.model;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.person.model.Person;
import giasuomt.demo.task.model.TaskAddress;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "area")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"}) 
public class Area extends AbstractEntity {
	
	protected String nation;
	
	protected String state;
	
	protected String provincialLevel;
	
	protected String district;
	
	protected String commune;
	
	protected String xRelCoo;	
	
	protected String yRelCoo;

	@OneToMany(mappedBy = "tempArea", fetch = FetchType.LAZY)
	@JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
	private Set<Person> personsWithTempArea=new HashSet<>();

	@OneToMany(mappedBy = "perArea", fetch = FetchType.LAZY)
	@JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
	private Set<Person> personsWithPerArea=new HashSet<>();

	@OneToMany(mappedBy = "relArea", fetch = FetchType.LAZY)
	@JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
	private Set<Person> personsWithRelArea=new HashSet<>();	

	
//	@OneToMany(mappedBy = "area", fetch = FetchType.LAZY)
//	@JsonIgnore  
//	Set<LearnerAndRegister> learnerAndRegisters;
	
//	@OneToMany(mappedBy = "area", fetch = FetchType.LAZY)
//	@JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
//	Set<TaskAddress> taskAddress;	
	
	
	
	//getter,setter kiểu fluentAPI
	public Area nation(String nation) {
		this.nation = nation;
		return this;
	}
	public Area state(String state) {
		this.state = state;
		return this;
	}
	public Area provincialLevel(String provincialLevel) {
		this.provincialLevel = provincialLevel;
		return this;
	}
	public Area district(String district) {
		this.district = district;
		return this;
	}
	public Area commune(String commune) {
		this.commune = commune;
		return this;
	}
	public Area xRelCoo(String xRelCoo) {
		this.xRelCoo = xRelCoo;
		return this;
	}
	public Area yRelCoo(String yRelCoo) {
		this.yRelCoo = yRelCoo;
		return this;
	}
}
