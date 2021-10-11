package giasuomt.demo.tutor.service;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.tutor.dto.SaveCertificateDto;
import giasuomt.demo.tutor.model.Certificate;
import giasuomt.demo.tutor.repository.ICertificateRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CertificateService implements ICertificateService {

	private ICertificateRepository iCertificateRepository;

	private MapDtoToModel mapDtoToModel;

	@Override
	public List<Certificate> findAll() {

		return iCertificateRepository.findAll();
	}

	public Certificate create(@Valid SaveCertificateDto dto) {
		Certificate certificate = new Certificate();

		return save(dto, certificate);
	}

	public Certificate update(SaveCertificateDto dto) {
		Certificate certificate = iCertificateRepository.getOne(dto.getId());

		return save(dto, certificate);
	}

	public Certificate save(SaveCertificateDto dto, Certificate certificate) {
		try {
			certificate = (Certificate) mapDtoToModel.map(dto, certificate);

			return iCertificateRepository.save(certificate);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;

	}

	public void deleteById(Long id) {
		try {

			iCertificateRepository.deleteById(id);
			;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// check id

	public boolean checkExistIdofCertificate(Long id) {
		return iCertificateRepository.countById(id) == 1;
	}

}
