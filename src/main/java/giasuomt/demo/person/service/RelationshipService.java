package giasuomt.demo.person.service;

import java.util.List;

import javax.management.relation.Relation;

import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.person.dto.PersonWithRelationship;
import giasuomt.demo.person.dto.SaveRelationshipDto;
import giasuomt.demo.person.model.Relationship;
import giasuomt.demo.person.repository.IPersonRepository;
import giasuomt.demo.person.repository.IRelationshipRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RelationshipService extends GenericService<Relationship, Long> implements IRelationshipService {

	private IRelationshipRepository iRelationshipRepository;
	
	private IPersonRepository iPersonRepository;
	
	private MapDtoToModel mapDtoToModel;
	
	@Override
	public List<Relationship> findAll() {
		
		return iRelationshipRepository.findAll();
	}
	//thêm Person trước xong sau đó đến RelationShip
	public Relationship save(SaveRelationshipDto dto,Relationship relationship)
	{
		relationship = (Relationship) mapDtoToModel.map(dto, relationship);
		
		relationship.setPersonA(iPersonRepository.getOne(dto.getIdPersonBy()));
		
		relationship.setPersonB(iPersonRepository.getOne(dto.getIdPersonBy()));
		
		return iRelationshipRepository.save(relationship);
		
	}
	@Override
	public Relationship create(SaveRelationshipDto dto) {
		// TODO Auto-generated method stub
		return null;
	}
	

	//Thêm cùng 1 lúc

	
	
}
