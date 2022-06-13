package giasuomt.demo.tags.model;
import javax.persistence.MappedSuperclass;
import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.commondata.model.AbstractEntityNotId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass //để các lớp con có thể kế thừa lại được các annotation của lớp này
public class Tag extends AbstractEntityNotId {
	protected String tagType;
	
	protected String tagGroup;
	
	protected String tagName;
	
	
}
