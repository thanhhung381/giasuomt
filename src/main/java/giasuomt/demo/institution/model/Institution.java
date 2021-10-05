package giasuomt.demo.institution.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.learnerAndRegister.model.LearnerAndRegister;
import giasuomt.demo.tutor.model.GraduatedStudent;
import giasuomt.demo.tutor.model.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "institution")
public class Institution extends AbstractEntity {
        private String name;
        
        private String nameAbbr;
        
        private String code;
        
        private String institutionTypes;
        
        @OneToMany(mappedBy = "institution", fetch = FetchType.EAGER)
        private Set<InstitutionAddress> addresses;
        
        
        @OneToMany(mappedBy = "institution", fetch = FetchType.EAGER)
        @JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
        Set<Major> majors;        
        
   //     @OneToMany(mappedBy = "institution", fetch = FetchType.LAZY)
   //     @JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
   //     Set<Student> students;

        @OneToMany(mappedBy = "institution", fetch = FetchType.LAZY)
        @JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
        Set<GraduatedStudent> graduatedStudents;
        
        @ManyToMany(mappedBy = "institutions", fetch = FetchType.LAZY)
        @JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
        private Set<LearnerAndRegister> learnerAndRegisters = new HashSet<>();
}