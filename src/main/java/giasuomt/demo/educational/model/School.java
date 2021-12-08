package giasuomt.demo.educational.model;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import giasuomt.demo.commondata.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "institution")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"}) 
public class School extends AbstractEntity {
    private String name;
    
    private String englishName;
    
    private String schoolTypes;
    
    private String district;
}
