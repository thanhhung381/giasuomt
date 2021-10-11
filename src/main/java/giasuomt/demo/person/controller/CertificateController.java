package giasuomt.demo.person.controller;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.person.dto.SaveCertificateDto;
import giasuomt.demo.person.model.Certificate;
import giasuomt.demo.person.service.ICertificateService;
import lombok.AllArgsConstructor;

@RequestMapping("/api/certificate")
@RestController
@AllArgsConstructor
public class CertificateController {

	private ICertificateService iCertificateService;

	@GetMapping("/findall")
	public ResponseEntity<Object> findall() {
		List<Certificate> certificates = iCertificateService.findAll();

		if (certificates.isEmpty())
			return ResponseHandler.getResponse("there is no data", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(certificates, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Object> create(@Valid @RequestBody SaveCertificateDto dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		Certificate createdCertificate = iCertificateService.create(dto);

		return ResponseHandler.getResponse(createdCertificate, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Object> update(@Valid @RequestBody SaveCertificateDto dto, BindingResult errors) {

		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		Certificate updatedCertificate = iCertificateService.update(dto);

		return ResponseHandler.getResponse(updatedCertificate, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		if (!iCertificateService.checkExistIdofCertificate(id))
			return ResponseHandler.getResponse("Do not exist Certificate", HttpStatus.BAD_REQUEST);

		iCertificateService.deleteById(id);

		return ResponseHandler.getResponse("Delete Successfully", HttpStatus.OK);
	}

}
