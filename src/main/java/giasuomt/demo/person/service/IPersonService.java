package giasuomt.demo.person.service;

import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import giasuomt.demo.commondata.generic.IGenericService;

import giasuomt.demo.person.dto.SavePersonDto;
import giasuomt.demo.person.model.Person;

public interface IPersonService extends IGenericService<SavePersonDto, Person, Long> {

	public Person create(SavePersonDto dto);

	public Person update(SavePersonDto dto);

	public Person save(SavePersonDto dto, Person person);

	public void delete(Long id);

	public Person findByTutorCode(String tutorCode);

	public boolean checkByTutorCodeExist(String tutorCode);

	public List<Person> findByPhones(String phones);

	public List<Person> findByEndPhone(String phones);

	public boolean checkByPhonesExist(String phones);

	public List<Person> findByFullnamesContain(String fullname);

	public boolean checkFullnameExistContaining(String fullname);

}
