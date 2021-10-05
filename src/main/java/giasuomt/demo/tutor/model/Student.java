package giasuomt.demo.tutor.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.commondata.util.DateTimeUtils;
import giasuomt.demo.institution.model.Institution;
import giasuomt.demo.institution.model.Major;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "student")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"}) //chống báo lỗi 
//@NamedEntityGraph(name = "student-tutor",attributeNodes = {@NamedAttributeNode("tutor.students")})
public class Student extends AbstractEntity {
		
		
	
        private String confirmImgs;
        
        private String nowLevel;
        
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATE_TIME_FORMAT) //Quy định date format khi nó add đối tượng thành Json để trả về Clients
        @DateTimeFormat(pattern = DateTimeUtils.DATE_TIME_FORMAT) //Quy định date format để lưu xuống database
        private LocalDateTime nowLevelUpdatedAt;
        
       // @ManyToOne
       // @JoinColumn(name = "university_id")
       // private Institution institution;
        
       // @ManyToOne
       // @JoinColumn(name = "major_id")
       // private Major major;
        
       // private String anotherMajor;
        
        
        
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "id_tutor")
        private Tutor tutor;
}