package giasuomt.demo.tutor.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.institution.model.Institution;
import giasuomt.demo.tutor.dto.CreateInstitutionDTO;
import giasuomt.demo.tutor.service.IInstitutionService;

@RestController // Tức là server sẽ trả JSON về client
@RequestMapping("/api/insitution") // Ghi nhận yêu cầu gọi api của Client
public class InstitutionController {
	@Autowired
	private IInstitutionService service;
	
	// Save Institution
	@PostMapping("")
	public ResponseEntity<Object> save(@Valid @RequestBody CreateInstitutionDTO dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST); // Trả về các messages lỗi, kèm theo
																				// HttpStatus BAD REQUEST [xem trong
																				// ResponsHandler.java]
		Institution addedInsitution = service.save(dto);
		return ResponseHandler.getResponse(addedInsitution, HttpStatus.CREATED); // Trả về http status là đã thành công
	}
}
