package giasuomt.demo.location.controller;
import java.util.List;
import java.util.Set;
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
import giasuomt.demo.learnerAndRegister.model.LearnerAndRegister;
import giasuomt.demo.location.dto.FindingDtoArea;
import giasuomt.demo.location.dto.SaveAreaDTO;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.location.service.IAreaService;
import lombok.AllArgsConstructor;

@RestController // Tức là server sẽ trả JSON về client
@RequestMapping("/api/area") // Ghi nhận yêu cầu gọi api của Client
@AllArgsConstructor
public class AreaController {

	private IAreaService iAreaService;

	// Show list of Area
	@GetMapping("/findAll")
	public ResponseEntity<Object> findAll() {
		List<Area> areas = iAreaService.findAll();
		if (areas.isEmpty())

			return ResponseHandler.getResponse("There is no data.", HttpStatus.OK);

		return ResponseHandler.getResponse(areas, HttpStatus.OK);
	}

	// Save Area
	@PostMapping("/create")
	public ResponseEntity<Object> create(@Valid @RequestBody SaveAreaDTO dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST); // Trả về các messages lỗi, kèm theo
																				// HttpStatus BAD REQUEST [xem trong
																				// ResponsHandler.java]
		Area addedArea = iAreaService.create(dto);
		
		return ResponseHandler.getResponse(addedArea, HttpStatus.CREATED); // Trả về http status là đã thành công
	}

	// update area
	@PutMapping("/update")
	public ResponseEntity<Object> update(@Valid @RequestBody SaveAreaDTO dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Area updatedArea = iAreaService.update(dto);
		
		return ResponseHandler.getResponse(updatedArea, HttpStatus.OK);
	}
	
	// delete area
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		if (!iAreaService.checkExistIdofArea(id))
			return ResponseHandler.getResponse("Don't have any Area id", HttpStatus.BAD_REQUEST);
		
		iAreaService.deleteById(id);
		
		return ResponseHandler.getResponse("Delete Successfully", HttpStatus.OK);
	}


	
	

	// findByNationAndProvincialLevelAndDistrictAndCommune
	@PostMapping("/findByBasicLocationInArea")
	public ResponseEntity<Object> findByNationAndProvincialLevelAndDistrictAndCommune(
			@Valid @RequestBody FindingDtoArea dtoFinding, BindingResult errors) {
		List<Area> areas = iAreaService.findByNationAndProvincialLevelAndDistrictAndCommune(dtoFinding);
		if (areas == null)
			return ResponseHandler.getResponse("Do not exist Area", HttpStatus.BAD_REQUEST);
		return ResponseHandler.getResponse(areas, HttpStatus.OK);
	}

	@GetMapping("/findLearnerAndRegistersById/{area-id}")
	public ResponseEntity<Object> findLearnerAndRegistersById(@PathVariable("area-id") Long areaId) {
		Set<LearnerAndRegister> learnerAndRegisters = iAreaService.findLearnerAndRegistersById(areaId);
		if (learnerAndRegisters == null)
			return ResponseHandler.getResponse("There is no data.", HttpStatus.OK);
		return ResponseHandler.getResponse(learnerAndRegisters, HttpStatus.OK); // Trả về roles, kèm theo HttpStatus OK
	}

	// code

}