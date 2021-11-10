package giasuomt.demo.person.service;

import java.util.LinkedList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.MapDtoToModel;
import giasuomt.demo.person.dto.SaveCertificateDto;
import giasuomt.demo.person.model.Certificate;
import giasuomt.demo.person.repository.ICertificateRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CertificateService extends GenericService<SaveCertificateDto, Certificate, Long>
		implements ICertificateService {

	private ICertificateRepository iCertificateRepository;

	private MapDtoToModel mapDtoToModel;

	@Override
	public List<Certificate> findAll() {

		return iCertificateRepository.findAll();
	}

	public Certificate create(SaveCertificateDto dto) {
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

	@Override
	public List<Certificate> createAll(List<SaveCertificateDto> dtos) {
		try {
			List<Certificate> certificates = new LinkedList<>();
			for (SaveCertificateDto dto : dtos) {
				Certificate certificate = new Certificate();
				certificate = (Certificate) mapDtoToModel.map(dto, certificate);
				certificates.add(certificate);
			}

			return iCertificateRepository.saveAll(certificates);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
