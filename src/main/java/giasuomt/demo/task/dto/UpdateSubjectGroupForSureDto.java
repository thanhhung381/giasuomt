package giasuomt.demo.task.dto;

import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import lombok.Getter;

@Getter
@Setter
public class UpdateSubjectGroupForSureDto {
	
	private Long id;
	
	private Set<String> idSubjectGroupForSures=new HashSet<>();
}
