package giasuomt.demo.person.service;
import java.util.List;

import javax.validation.Valid;
import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.person.dto.SaveCertificateDto;
import giasuomt.demo.person.model.Certificate;
import giasuomt.demo.person.model.Person;

public interface ICertificateService  {
	
	public List<Certificate> findAll(); 
	
	public Certificate create(@Valid SaveCertificateDto dto);

	public Certificate save(SaveCertificateDto dto, Certificate certificate);

	public Certificate update(SaveCertificateDto dto);

	public boolean checkExistIdofCertificate(Long id);

	public void deleteById(Long id);
}
