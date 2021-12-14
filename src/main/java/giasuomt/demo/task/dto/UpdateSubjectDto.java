package giasuomt.demo.task.dto;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UpdateSubjectDto {
	
	private String id;
	
	private String subjectApperance;

	private String subjectNote;
	
	private List<Long> subjectIds=new LinkedList<>();
	
}
