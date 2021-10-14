package giasuomt.demo.location.controller;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.location.dto.FindingDtoArea;
import giasuomt.demo.location.dto.SaveAreaDto;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.location.service.IAreaService;
import lombok.AllArgsConstructor;

@RestController // Tức là server sẽ trả JSON về client
@RequestMapping("/api/area") // Ghi nhận yêu cầu gọi api của Client
@AllArgsConstructor
public class AreaController extends GenericController<SaveAreaDto, Area, Long, BindingResult> {

	private IAreaService iAreaService;

	

	// findByNationAndProvincialLevelAndDistrictAndCommune
	@PostMapping("/findByBasicLocationInArea")
	public ResponseEntity<Object> findByNationAndProvincialLevelAndDistrictAndCommune(
			@Valid @RequestBody FindingDtoArea dtoFinding, BindingResult errors) {
		List<Area> areas = iAreaService.findByNationAndProvincialLevelAndDistrictAndCommune(dtoFinding);
		if (areas == null)
			return ResponseHandler.getResponse("Do not exist Area", HttpStatus.BAD_REQUEST);
		return ResponseHandler.getResponse(areas, HttpStatus.OK);
	}



	// code

}