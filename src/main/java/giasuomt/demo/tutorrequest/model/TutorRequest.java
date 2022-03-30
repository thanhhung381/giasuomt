package giasuomt.demo.tutorrequest.model;

import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import giasuomt.demo.commondata.model.AbstractEntityNotId;
import giasuomt.demo.commondata.util.DateUtils;
import lombok.Getter;

@Getter
@Setter
@Entity
@Table(name = "TutorRequest")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class TutorRequest  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT) // Quy định date format khi nó add đối
	// tượng thành Json để trả về
	// Clients
	@DateTimeFormat(pattern = DateUtils.DATE_FORMAT) // Quy định date format để lưu xuống database
	private LocalDateTime createdAt;

	private String fromUrl;
	
	private Long chosenTutorId;
	
	private String phones;
	
	private String username;
	
	private String whenToContact;
	
	private String requireContent;
	
	private String local;
}
