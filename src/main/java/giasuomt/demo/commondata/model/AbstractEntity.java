package giasuomt.demo.commondata.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import giasuomt.demo.commondata.util.DateTimeUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass //để các lớp con có thể kế thừa lại được các annotation của lớp này
@EntityListeners(AuditingEntityListener.class) //để model này lấy được @EnableJpaAuditing đã Config ở bên JpaConfig.java để @CreatedDate,@LastModifiedDate có hiệu lực
public class AbstractEntity extends AbstractEntityNotId {
	
	@Id //Quy định khoá chính
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Sinh ra các giá trị là độc lập cho các model khác nhau
	@Column(updatable = false) //Column này ko update được
	protected Long id; //(khai báo là protected để các lớp con có thể truy cập vô để kế thừa)
	
	
//
//	@CreatedBy
//	protected String createdBy;  
//	
//	@LastModifiedBy
//	protected String updatedBy;
//	
//	

	
	
//	/*Constructor*/ //khởi tạo constructor để createdAt, updatedAt ko bị null
//	public AbstractEntity() {
//		createdAt = LocalDateTime.now();
//		updatedAt = LocalDateTime.now();
//	}
	
	
}
