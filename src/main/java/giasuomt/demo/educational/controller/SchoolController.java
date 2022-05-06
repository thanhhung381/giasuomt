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
import giasuomt.demo.educational.dto.SaveSchoolDto;
import giasuomt.demo.educational.model.School;
import giasuomt.demo.educational.service.ISchoolService;
import lombok.AllArgsConstructor;

@RequestMapping("/api/school")
@RestController
@AllArgsConstructor
public class SchoolController extends GenericController<SaveSchoolDto, School, Long> {

	private ISchoolService iSchoolService;

	@GetMapping("/findByName/{name}")
	public ResponseEntity<Object> findByName(@RequestParam("name") String name) {
		List<School> schoolsWithName = iSchoolService.findByNameContaining(name.toUpperCase());
		if (schoolsWithName.isEmpty()) {
			List<School> schoolsWithEnglishName = iSchoolService
					.findByEnglishNameContaining(StringUltilsForAreaID.removeAccent(name.toUpperCase()));
			if (schoolsWithEnglishName.isEmpty())
				return ResponseHandler.getResponse("Can't find any schools", HttpStatus.BAD_REQUEST);
			return ResponseHandler.getResponse(schoolsWithEnglishName, HttpStatus.OK);
		}

		return ResponseHandler.getResponse(schoolsWithName, HttpStatus.OK);
	}

	@GetMapping("/findByDistrictAndName/{district}/{name}")
	public ResponseEntity<Object> findByDistrictAndName(@RequestParam("district") String district,
			@RequestParam("name") String name) {
		List<School> schoolsWithName = iSchoolService.findByDistrictAndNameContaining(district, name.toUpperCase());
		if (schoolsWithName.isEmpty()) {
			List<School> schoolsWithEnglishName = iSchoolService.findByDistrictAndEnglishNameContaining(district,
					StringUltilsForAreaID.removeAccent(name.toUpperCase()));
			if (schoolsWithEnglishName.isEmpty())
				return ResponseHandler.getResponse("Can't find any schools", HttpStatus.BAD_REQUEST);
			return ResponseHandler.getResponse(schoolsWithEnglishName, HttpStatus.OK);
		}

		return ResponseHandler.getResponse(schoolsWithName, HttpStatus.OK);
	}

	@GetMapping("/findBySchoolTypesAndName/{schoolTypes}/{name}")
	public ResponseEntity<Object> findBySchoolTypesAndName(@RequestParam("schoolTypes") String schoolTypes,
			@RequestParam("name") String name) {
		List<School> schoolsWithName = iSchoolService.findBySchoolTypesAndNameContaining(schoolTypes,
				name.toUpperCase());
		if (schoolsWithName.isEmpty()) {
			List<School> schoolsWithEnglishName = iSchoolService.findBySchoolTypesAndEnglishNameContaining(schoolTypes,
					StringUltilsForAreaID.removeAccent(name.toUpperCase()));
			if (schoolsWithEnglishName.isEmpty())
				return ResponseHandler.getResponse("Can't find any schools", HttpStatus.BAD_REQUEST);
			return ResponseHandler.getResponse(schoolsWithEnglishName, HttpStatus.OK);
		}

		return ResponseHandler.getResponse(schoolsWithName, HttpStatus.OK);
	}

	@GetMapping("/findBySchoolTypesAndDistrictAndName/{schoolTypes}/{district}/{name}")
	public ResponseEntity<Object> findBySchoolTypesAndName(@RequestParam("schoolTypes") String schoolTypes,
			@RequestParam("district") String district, @RequestParam("name") String name) {
		List<School> schoolsWithName = iSchoolService.findBySchoolTypesAndDistrictAndNameContaining(schoolTypes,
				district, name.toUpperCase());
		if (schoolsWithName.isEmpty()) {
			List<School> schoolsWithEnglishName = iSchoolService.findBySchoolTypesAndDistrictAndEnglishNameContaining(
					schoolTypes, district, StringUltilsForAreaID.removeAccent(name.toUpperCase()));
			if (schoolsWithEnglishName.isEmpty())
				return ResponseHandler.getResponse("Can't find any schools", HttpStatus.BAD_REQUEST);
			return ResponseHandler.getResponse(schoolsWithEnglishName, HttpStatus.OK);
		}

		return ResponseHandler.getResponse(schoolsWithName, HttpStatus.OK);
	}

}
