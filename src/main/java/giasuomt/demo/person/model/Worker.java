package giasuomt.demo.person.model;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.LastModifiedDate;
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
@Table(name = "worker")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" }) // chống báo lỗi
public class Worker extends AbstractEntity {
	private String confirmImgs;

	private String company;
	
	private String jobPosition;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATE_TIME_FORMAT) //Quy định date format khi nó add đối tượng thành Json để trả về Clients
	@LastModifiedDate //Annotation để JPA tự cập nhật cho mình (nhưng muốn sử dụng annotation này thì phải config @EnableJpaAuditing ở trong JpaConfig)
	@DateTimeFormat(pattern = DateTimeUtils.DATE_TIME_FORMAT) //Quy định date format để lưu xuống database
	@Column(name = "company_updated_at", nullable = false)
	private LocalDateTime companyUpdatedAt;

	

	@ManyToOne
	@JoinColumn(name = "person_id")
	@JsonIgnore
	private Person person;
}
