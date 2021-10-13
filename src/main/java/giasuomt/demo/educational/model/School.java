package giasuomt.demo.educational.model;
import javax.persistence.Entity;
import javax.persistence.Table;
import giasuomt.demo.commondata.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "institution")
public class School extends AbstractEntity {
    private String name;
    
    private String nameAbbr;
    
    private String code;
    
    private String schoolTypes;
}
