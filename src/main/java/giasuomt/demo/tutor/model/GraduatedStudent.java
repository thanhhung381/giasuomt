package giasuomt.demo.tutor.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.institution.model.Institution;
import giasuomt.demo.institution.model.Major;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "graduated_student")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"}) 
public class GraduatedStudent extends AbstractEntity {
        private String diplomaImgs;
        
        private String graduatedYear;
        
		
//		  @ManyToOne
//		  @JoinColumn(name = "university_id") 
//		  private Institution institution;
		  
		  
//		  @ManyToOne
//		  @JoinColumn(name = "major_id") 
//		  private Major major;
		  
//		  private String anotherMajor;
		 
        
	        @ManyToOne(fetch = FetchType.LAZY)
	        @JoinColumn(name = "tutor_id")
	        @JsonIgnore
	        private Tutor tutor;
}