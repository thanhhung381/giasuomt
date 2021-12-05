package giasuomt.demo.tags.model;
import javax.persistence.MappedSuperclass;
import giasuomt.demo.commondata.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass //để các lớp con có thể kế thừa lại được các annotation của lớp này
public class Tag extends AbstractEntity {
	private String tagType;
	
	private String tagGroup;
	
	private String tagName;
	
	private String description;
}
