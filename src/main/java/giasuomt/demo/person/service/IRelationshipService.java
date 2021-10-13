package giasuomt.demo.person.service;

import java.util.List;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.person.dto.SaveRelationshipDto;
import giasuomt.demo.person.model.Person;
import giasuomt.demo.person.model.Relationship;

public interface IRelationshipService extends IGenericService<Relationship, Long> {

	public List<Relationship> findAll() ;
	
	public Relationship save(SaveRelationshipDto dto,Relationship relationship);
	
	public Relationship create(SaveRelationshipDto dto);
}
