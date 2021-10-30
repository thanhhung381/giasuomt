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
public class AbstractEntity implements Serializable{
//	implements Serializable
	@Id //Quy định khoá chính
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Sinh ra các giá trị là độc lập cho các model khác nhau
	@Column(updatable = false) //Column này ko update được
	protected Long id; //(khai báo là protected để các lớp con có thể truy cập vô để kế thừa)
	
	@Version 
	Long version; //version có thể dùng để truyền vào cache
	
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATE_TIME_FORMAT) //Quy định date format khi nó add đối tượng thành Json để trả về Clients
//	@CreatedDate //Annotation để JPA tự thêm cho mình (nhưng muốn sử dụng annotation này thì phải config @EnableJpaAuditing ở trong JpaConfig)	
//	@DateTimeFormat(pattern = DateTimeUtils.DATE_TIME_FORMAT) //Quy định date format để lưu xuống database
//	@Column(name = "created_at", nullable = false, updatable = false) //(ngày khởi tạo ko đc null, và ko đc updated)
//	protected LocalDateTime createdAt;
//	
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATE_TIME_FORMAT) //Quy định date format khi nó add đối tượng thành Json để trả về Clients
//	@LastModifiedDate //Annotation để JPA tự cập nhật cho mình (nhưng muốn sử dụng annotation này thì phải config @EnableJpaAuditing ở trong JpaConfig)
//	@DateTimeFormat(pattern = DateTimeUtils.DATE_TIME_FORMAT) //Quy định date format để lưu xuống database
//	@Column(name = "updated_at", nullable = false)
//	protected LocalDateTime updatedAt;
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
	
	
	
	/*getter, setter */
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public int getVersion() {
//		return version;
//	}
//
//	public void setVersion(int version) {
//		this.version = version;
//	}

//	public LocalDateTime getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(LocalDateTime createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public LocalDateTime getUpdatedAt() {
//		return updatedAt;
//	}
//
//	public void setUpdatedAt(LocalDateTime updatedAt) {
//		this.updatedAt = updatedAt;
//	}
//
//
//	public String getCreatedBy() {
//		return createdBy;
//	}
//
//
//	public void setCreatedBy(String createdBy) {
//		this.createdBy = createdBy;
//	}
//
//
//	public String getUpdatedBy() {
//		return updatedBy;
//	}
//
//
//	public void setUpdatedBy(String updatedBy) {
//		this.updatedBy = updatedBy;
//	}
	
	
	
}
