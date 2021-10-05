package giasuomt.demo.tutor.dto;

import java.util.HashSet;
import java.util.Set;

import giasuomt.demo.location.model.Area;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTutorDTO {
	 private String tutorCode;
	 
	 private Area tempArea;
	 
	 private Set<CreateStudentDTO> students = new HashSet<>();
}
