package giasuomt.demo.tutor.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.institution.model.Institution;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "school_teacher")
public class SchoolTeacher extends AbstractEntity {
        private String confirmImgs;
        
        @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
        @JoinTable(name = "school_teacher_institution",
                           joinColumns = @JoinColumn(name = "school_teacher_id"),
                           inverseJoinColumns = @JoinColumn(name = "institution_id"))
        private Set<Institution> institutions = new HashSet<>();
        
        private String subject;
        
        
        @ManyToOne
        @JoinColumn(name = "tutor_id")
        private Tutor tutor;
}