package giasuomt.demo.uploadfile.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "feedback_image")
@Getter
@Setter
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class FeedBackImage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nameFile;
	
	private String contentType;
	
	private Long size;
	
	@Lob
	@org.hibernate.annotations.Type(type = "org.hibernate.type.ImageType")
	private byte[] data;
	
	

}
