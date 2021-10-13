package giasuomt.demo.person.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.person.dto.SaveCertificateDto;
import giasuomt.demo.person.dto.SaveRelationshipDto;
import giasuomt.demo.person.model.Certificate;
import giasuomt.demo.person.model.Relationship;
import giasuomt.demo.person.service.IRelationshipService;
import lombok.AllArgsConstructor;

@RequestMapping("/api/relationship")
@RestController
@AllArgsConstructor
public class TestController {
	
		private IRelationshipService iRelationshipService;
		
		@GetMapping("/findall")
		public ResponseEntity<Object> findall() {
			List<Relationship> certificates = iRelationshipService.findAll();

			if (certificates.isEmpty())
				return ResponseHandler.getResponse("there is no data", HttpStatus.BAD_REQUEST);

			return ResponseHandler.getResponse(certificates, HttpStatus.OK);
		}

		@PostMapping("/create")
		public ResponseEntity<Object> create(@Valid @RequestBody SaveRelationshipDto dto, BindingResult errors) {
			if (errors.hasErrors())
				return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

			Relationship createdCertificate = iRelationshipService.create(dto);

			return ResponseHandler.getResponse(createdCertificate, HttpStatus.CREATED);
		}
}
