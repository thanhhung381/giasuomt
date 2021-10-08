package giasuomt.demo.tutor.model;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import giasuomt.demo.commondata.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "school_teacher")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"}) 
public class SchoolTeacher extends AbstractEntity {
	
        private String confirmImgs;
        
        
        String institutionName;
        
        String institutionAbbrName;
        
        String institutionCode;
        
        String institutionType;
        
        
        private String subject;
        
        
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "tutor_id")
        @JsonIgnore
        private Tutor tutor;
}