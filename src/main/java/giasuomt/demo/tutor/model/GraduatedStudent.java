package giasuomt.demo.tutor.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.institution.model.Institution;
import giasuomt.demo.institution.model.Major;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "graduated_student")
public class GraduatedStudent extends AbstractEntity {
        private String diplomaImgs;
        
        private String graduatedYear;
        
        @ManyToOne
        @JoinColumn(name = "university_id")
        private Institution institution;
        
        @ManyToOne
        @JoinColumn(name = "major_id")
        private Major major;
        
        private String anotherMajor;
        
        @ManyToOne
        @JoinColumn(name = "tutor_id")
        private Tutor tutor;
}