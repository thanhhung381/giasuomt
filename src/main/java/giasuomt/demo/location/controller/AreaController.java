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
import giasuomt.demo.location.dto.FindingVietnamAreaDto;
import giasuomt.demo.location.dto.SaveAreaDto;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.location.service.IAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController // Tức là server sẽ trả JSON về client
@RequestMapping("/api/area") // Ghi nhận yêu cầu gọi api của Client
@AllArgsConstructor
@Api(description = "\"Api tạo Area\"" )
public class AreaController extends GenericController<SaveAreaDto, Area, String> {

	private IAreaService iAreaService;

	

	// findByNationAndProvincialLevelAndDistrictAndCommune
	@ApiOperation(notes = "tìm kiếm ở Việt Nam", value = "findByNationAndProvincialLevelAndDistrictAndCommune")
	@PostMapping("/findVietnamArea")
	public ResponseEntity<Object> findByNationAndProvincialLevelAndDistrictAndCommune(
			@Valid @RequestBody FindingVietnamAreaDto findingVietnamAreaDto, BindingResult errors) {
		List<Area> areas = iAreaService.findByNationAndProvincialLevelAndDistrictAndCommune(findingVietnamAreaDto);
		if (areas == null)
			return ResponseHandler.getResponse("Do not exist Area", HttpStatus.BAD_REQUEST);
		return ResponseHandler.getResponse(areas, HttpStatus.OK);
	}
	



	// code

}