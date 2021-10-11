package giasuomt.demo.tutor.service;

import java.util.List;

import javax.validation.Valid;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.tutor.dto.SaveCertificateDto;
import giasuomt.demo.tutor.model.Certificate;

public interface ICertificateService {
	public Certificate create(@Valid SaveCertificateDto dto);

	public Certificate save(SaveCertificateDto dto, Certificate certificate);

	public Certificate update(SaveCertificateDto dto);

	public boolean checkExistIdofCertificate(Long id);

	public void deleteById(Long id);

	List<Certificate> findAll();

}
