package giasuomt.demo.person.dto;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRegisteredSubject {

	private Long id;

	private List<Long> registeredSubjectIds = new LinkedList<>();

}
