package giasuomt.demo.educational.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.commondata.generic.StringUltilsForAreaID;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.educational.dto.SaveUniversityDto;
import giasuomt.demo.educational.model.University;
import giasuomt.demo.educational.service.IUniversityService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/universty")
@AllArgsConstructor
public class UniversityController extends GenericController<SaveUniversityDto, University, Long, BindingResult> {

	private IUniversityService iUniversityService;

	@GetMapping("/findByName/{name}")
	public ResponseEntity<Object> findByName(@RequestParam("name") String name) {
		List<University> universitiesByName = iUniversityService.findByNameContaining(name.toUpperCase());
		if (universitiesByName.isEmpty()) {
			List<University> universitiesByEngLishName = iUniversityService
					.findByEnglishNameContaining(StringUltilsForAreaID.removeAccent(name.toUpperCase()));
			if (universitiesByEngLishName.isEmpty())
				return ResponseHandler.getResponse("can't find any universities", HttpStatus.BAD_REQUEST);
			return ResponseHandler.getResponse(universitiesByEngLishName, HttpStatus.OK);
		}
		return ResponseHandler.getResponse(universitiesByName, HttpStatus.OK);
	}

	@GetMapping("/findByCode/{code}")
	public ResponseEntity<Object> findByCode(@RequestParam("code") String code) {
		List<University> universitiesByCode = iUniversityService.findByCode(code);
		if (universitiesByCode.isEmpty()) {
			return ResponseHandler.getResponse("can't find any universities", HttpStatus.BAD_REQUEST);
		}
		return ResponseHandler.getResponse(universitiesByCode, HttpStatus.OK);
	}

	@GetMapping("/findByNameAbbr/{nameAbbr}")
	public ResponseEntity<Object> findByNameAbbr(@RequestParam("nameAbbr") String nameAbbr) {
		List<University> universitiesByNameAbbr = iUniversityService.findByNameAbbr(nameAbbr);
		if (universitiesByNameAbbr.isEmpty()) {
			return ResponseHandler.getResponse("can't find any universities", HttpStatus.BAD_REQUEST);
		}
		return ResponseHandler.getResponse(universitiesByNameAbbr, HttpStatus.OK);
	}

	@GetMapping("/findByUniversityTypesAndName/{universityTypes}/{name}")
	public ResponseEntity<Object> findByUniversityTypesAndName(@RequestParam("universityTypes") String universityTypes,
			@RequestParam("name") String name) {
		List<University> universitiesByUniverTypesAndName = iUniversityService
				.findByUniversityTypesContainingAndNameContaining(universityTypes, name.toUpperCase());
		if (universitiesByUniverTypesAndName.isEmpty()) {
			List<University> universitiesByUniverTypesAndEngName = iUniversityService
					.findByUniversityTypesContainingAndEnglishNameContaining(universityTypes, StringUltilsForAreaID.removeAccent(name.toUpperCase()));
			if (universitiesByUniverTypesAndEngName.isEmpty())
				return ResponseHandler.getResponse("can't find any universities", HttpStatus.BAD_REQUEST);
			return ResponseHandler.getResponse(universitiesByUniverTypesAndEngName, HttpStatus.OK);
		}
		return ResponseHandler.getResponse(universitiesByUniverTypesAndName, HttpStatus.OK);
	}
}
