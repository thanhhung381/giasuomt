package giasuomt.demo.uploadfile.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "file")
@Getter
@Setter
public class FileEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nameFile;
	
	private String contentType;
	
	private Long size;
	
	@Lob
	private byte[] data;
	
	
	
}
