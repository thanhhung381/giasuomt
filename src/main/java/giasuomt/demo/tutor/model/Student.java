package giasuomt.demo.tutor.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class Student extends AbstractEntity {
        private String confirmImgs;
        
        private String nowLevel;
        
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATE_TIME_FORMAT) //Quy định date format khi nó add đối tượng thành Json để trả về Clients
        @DateTimeFormat(pattern = DateTimeUtils.DATE_TIME_FORMAT) //Quy định date format để lưu xuống database
        private LocalDateTime nowLevelUpdatedAt;
        
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