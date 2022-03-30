package giasuomt.demo.task.dto;

import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;

@Getter
@Setter
public class UpdateSubjectGroupForSureDto {
	
	private Long id;
	
	private List<Long> idSubjectGroupForSures=new LinkedList<>();
}
