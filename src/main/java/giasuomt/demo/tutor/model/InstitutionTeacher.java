package giasuomt.demo.tutor.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import giasuomt.demo.commondata.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "institution_teacher")
public class InstitutionTeacher extends AbstractEntity {        
        private String confirmImgs;
        
        private String institutionType;
        
        private String institutionName;
        
        private String subject;
        
//        @ManyToOne
//        @JoinColumn(name = "tutor_id")
//        private Tutor tutor;
}