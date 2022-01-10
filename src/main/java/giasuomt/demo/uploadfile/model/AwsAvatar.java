package giasuomt.demo.uploadfile.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "AWS_avatar")
@Getter
@Setter
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class AwsAvatar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String urlImage;
	
}
