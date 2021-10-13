package giasuomt.demo.educational.model;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import giasuomt.demo.commondata.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "university")
public class University extends AbstractEntity {
    private String name;
    
    private String nameAbbr;
    
    private String code;
    
    private String universityTypes;
    
    
    @OneToMany(mappedBy = "university")
    Set<Major> majors; 
}
