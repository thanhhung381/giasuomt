package giasuomt.demo.location.model;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import giasuomt.demo.commondata.model.AbstractEntityNotId;
import giasuomt.demo.person.model.Tutor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "area")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"}) 
public class Area extends AbstractEntityNotId {
	@Id
	private String id;
	protected String nation;
	protected String state;	
	protected String provincialLevel;	
	protected String district;	
	protected String commune;	
	protected String xRelCoo;		
	protected String yRelCoo;
	@OneToMany(mappedBy = "tutorAddressArea", fetch = FetchType.LAZY)
	@JsonIgnore 
	private Set<Tutor> tutorsWithAddressArea=new HashSet<>();
	@ManyToMany(mappedBy = "relArea", fetch = FetchType.LAZY)
	@JsonIgnore 
	private Set<Tutor> tutorsWithRelArea=new HashSet<>();
	@OneToMany(mappedBy = "area", fetch = FetchType.LAZY)
	@JsonIgnore 
	private Set<RegisterAndLearnerAddress> registerAndLearnerAddresses = new HashSet<>();	
	@OneToMany(mappedBy = "area", fetch = FetchType.LAZY)
	@JsonIgnore  
	private Set<TaskPlaceAddress> taskPlaceAddresses = new HashSet<>();		
	//getter,setter kiểu fluentAPI

}