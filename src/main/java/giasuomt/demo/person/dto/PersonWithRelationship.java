package giasuomt.demo.person.dto;

import java.util.ArrayList;
import java.util.List;

import giasuomt.demo.person.model.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonWithRelationship {
	
	private SavePersonDto personWith;
	
	private SavePersonDto personBy;
	
	private SaveRelationshipDto saveRelationshipDto;
}
