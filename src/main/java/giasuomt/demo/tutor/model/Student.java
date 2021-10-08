package giasuomt.demo.tutor.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.commondata.util.DateTimeUtils;
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
        
        
        String institutionName;
        
        String institutionAbbrName;
        
        String institutionCode;
        
        String institutionType;
        
        
        String majorName;
        
        String majorCode;
        

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "tutor_id")
        @JsonIgnore
        private Tutor tutor;
        
        public Student updateConfirm(String conString)
        {
        	this.confirmImgs=conString;
        	return this;
        }
        public Student updateNowLevel(String nowlevel)
        {
        	this.nowLevel=nowlevel;
        	return this;
        }
        public Student updateNowLevelUpdateAt(LocalDateTime  NewLevelUpdateAt)
        {
        	this.nowLevelUpdatedAt=NewLevelUpdateAt;
        	return this;
        }
        
        
        
}