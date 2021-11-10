package giasuomt.demo.educational.model;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
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
@Table(name = "university")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"}) 
public class University extends AbstractEntity {
    private String name;
    
    private String nameAbbr;
    
    private String code;
    
    private String universityTypes;
    
    
    @OneToMany(mappedBy = "university")
    @JsonIgnore
    private List<Major> majors=new LinkedList<>(); 
}
