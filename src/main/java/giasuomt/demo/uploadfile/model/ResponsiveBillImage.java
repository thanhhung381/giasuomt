package giasuomt.demo.uploadfile.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsiveBillImage {
	private Long id;
	
	private String name;
	
	private Long size;
	
	private String url;
	
	private String contentType;
}
