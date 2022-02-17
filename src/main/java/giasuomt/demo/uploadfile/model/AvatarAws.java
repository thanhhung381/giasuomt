package giasuomt.demo.uploadfile.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import giasuomt.demo.commondata.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "AWS_avatar")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class AvatarAws extends AbstractEntity {
	
	private String urlAvatar;
	
}
