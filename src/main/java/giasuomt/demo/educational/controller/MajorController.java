package giasuomt.demo.educational.controller;

import java.util.List;

import org.springframework.data.repository.query.Param;
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
import giasuomt.demo.educational.dto.SaveMajorDto;
import giasuomt.demo.educational.model.Major;
import giasuomt.demo.educational.service.IMajorService;
import lombok.AllArgsConstructor;

@RequestMapping("/api/major")
@RestController
@AllArgsConstructor
public class MajorController extends GenericController<SaveMajorDto, Major, Long, BindingResult> {

	private IMajorService iMajorService;

	@GetMapping("/findByCode/{code}")
	public ResponseEntity<Object> findByCode(@RequestParam("code") String code) {
		List<Major> majors = iMajorService.findByCode(code);
		if (majors.isEmpty())
			return ResponseHandler.getResponse("cant find any majors", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(majors, HttpStatus.OK);
	}

	@GetMapping("/findByName/{name}")
	public ResponseEntity<Object> findByName(@RequestParam("name") String name) {
		List<Major> majors = iMajorService.findByNameContaining(name.toUpperCase());
		if (majors.isEmpty()) {
			List<Major> majorWithEnglishName = iMajorService.findByEnglishNameContaining(StringUltilsForAreaID.removeAccent(name.toUpperCase()));
			if (majorWithEnglishName.isEmpty())
				return ResponseHandler.getResponse("cant find any majors", HttpStatus.BAD_REQUEST);
			return ResponseHandler.getResponse(majorWithEnglishName, HttpStatus.OK);
		}
		return ResponseHandler.getResponse(majors, HttpStatus.OK);
	}

	@GetMapping("/findByUniversityIDAndCode/{idUniversity}/{code}")
	public ResponseEntity<Object> findByUniversityIdAndCode(@RequestParam("idUniversity") Long idUniversity,
			@RequestParam("code") String code) {
		List<Major> majors = iMajorService.findByUniversityIdAndCode(idUniversity, code);
		if (majors.isEmpty())

			return ResponseHandler.getResponse("cant find any majors", HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse(majors, HttpStatus.OK);
	}

	@GetMapping("/findByUniversityIDAndName/{idUniversity}/{name}")
	public ResponseEntity<Object> findByUniversityIdAndName(@RequestParam("idUniversity") Long idUniversity,
			@RequestParam("name") String name) {
		List<Major> majors = iMajorService.findByUniversityIdAndName(idUniversity, name.toUpperCase());
		if (majors.isEmpty()) {
			List<Major> majorWithEnglishName = iMajorService.findByUniversityIdAndEnglishName(idUniversity,
					StringUltilsForAreaID.removeAccent(name.toUpperCase()));
			if (majorWithEnglishName.isEmpty())
				return ResponseHandler.getResponse("cant find any majors", HttpStatus.BAD_REQUEST);
			return ResponseHandler.getResponse(majorWithEnglishName, HttpStatus.OK);

		}

		return ResponseHandler.getResponse(majors, HttpStatus.OK);
	}

}
