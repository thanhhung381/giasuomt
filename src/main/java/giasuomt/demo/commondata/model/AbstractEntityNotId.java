package giasuomt.demo.commondata.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import giasuomt.demo.commondata.util.DateTimeUtils;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@MappedSuperclass //để các lớp con có thể kế thừa lại được các annotation của lớp này
@EntityListeners(AuditingEntityListener.class) //để model này lấy được @EnableJpaAuditing đã Config ở bên JpaConfig.java để @CreatedDate,@LastModifiedDate có hiệu lực
public class AbstractEntityNotId  implements Serializable{
	@Version 
	protected Long version; //version có thể dùng để truyền vào cache
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATE_TIME_FORMAT) //Quy định date format khi nó add đối tượng thành Json để trả về Clients
	@CreatedDate //Annotation để JPA tự thêm cho mình (nhưng muốn sử dụng annotation này thì phải config @EnableJpaAuditing ở trong JpaConfig)	
	@DateTimeFormat(pattern = DateTimeUtils.DATE_TIME_FORMAT) //Quy định date format để lưu xuống database
	@Column(name = "created_at", nullable = false, updatable = false) //(ngày khởi tạo ko đc null, và ko đc updated)
	protected LocalDateTime createdAt;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATE_TIME_FORMAT) //Quy định date format khi nó add đối tượng thành Json để trả về Clients
	@LastModifiedDate //Annotation để JPA tự cập nhật cho mình (nhưng muốn sử dụng annotation này thì phải config @EnableJpaAuditing ở trong JpaConfig)
	@DateTimeFormat(pattern = DateTimeUtils.DATE_TIME_FORMAT) //Quy định date format để lưu xuống database
	@Column(name = "updated_at", nullable = false)
	protected LocalDateTime updatedAt;
}
