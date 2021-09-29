package giasuomt.demo.institution.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.tutor.model.GraduatedStudent;
import giasuomt.demo.tutor.model.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "major")
public class Major extends AbstractEntity {        
        private String name;
        
        private String code;
        
        @ManyToOne
        @JoinColumn(name = "university_id")
        private Institution institution;
        
        @OneToMany(mappedBy = "major", fetch = FetchType.LAZY)
        @JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
        Set<Student> students;

        @OneToMany(mappedBy = "major", fetch = FetchType.LAZY)
        @JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
        Set<GraduatedStudent> graduatedStudents;
}