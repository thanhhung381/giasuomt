package giasuomt.demo.task.dto;

import java.util.LinkedList;
import java.util.List;

import giasuomt.demo.task.util.ApplicationSign;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UpdateApplicationSignDto {
	private Long id;
	
	private List<ApplicationSign> applicationSigns=new LinkedList<>();
}
