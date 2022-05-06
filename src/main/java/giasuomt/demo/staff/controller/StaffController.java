package giasuomt.demo.staff.controller;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;

import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.staff.dto.SaveStaffDto;
import giasuomt.demo.staff.dto.UpdateAvatarStaff;
import giasuomt.demo.staff.model.Staff;
import giasuomt.demo.staff.service.IStaffService;
import lombok.AllArgsConstructor;

@RequestMapping("/api/staff")
@RestController
@AllArgsConstructor
public class StaffController extends GenericController<SaveStaffDto, Staff, Long> {
	
	
	private IStaffService iStaffService;
	
	@PutMapping("/updateAvatarStaff")
	public ResponseEntity<Object> updateAvatarStaff(@RequestBody UpdateAvatarStaff dto,
			BindingResult errors) {

		Staff staff = iStaffService.updateAvatarStaff(dto);

		if (errors.hasErrors()) {
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		}

		return ResponseHandler.getResponse(staff, HttpStatus.OK);

	}
}
