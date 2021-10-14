package giasuomt.demo.person.service;

import java.util.List;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.person.dto.SavePersonDto;
import giasuomt.demo.person.model.Person;

public interface IPersonService {

	public List<Person> findAll(); 
	
	public Person create(SavePersonDto dto);

	public Person update(SavePersonDto dto);

	public Person save(SavePersonDto dto, Person person);

	public void delete(Long id);

}
