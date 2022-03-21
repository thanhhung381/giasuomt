package giasuomt.demo.commondata.generic;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import giasuomt.demo.commondata.model.AbstractEntityNotId;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class GenericController<DTO, T extends AbstractEntityNotId, ID, BindingResult> {
	
	@Autowired
	private IGenericService<DTO, T, ID> iGenericService;
	

	@GetMapping("/findAll")
//	@PreAuthorize("hasAuthority('tutor-role')")
	public ResponseEntity<Object> findall() {
		List<T> ts = iGenericService.findAll();
		if (ts==null)
			return ResponseHandler.getResponse("There is no data.", HttpStatus.BAD_REQUEST);
		
		
		return ResponseHandler.getResponse(ts, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Object> create(@Valid @RequestBody DTO dto, BindingResult errors) {
		if (((Errors) errors).hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		T createdT = iGenericService.create(dto);
		
		return ResponseHandler.getResponse(createdT, HttpStatus.OK);
	}
	
	@PostMapping("/createAll")
	public ResponseEntity<Object> create(@Valid @RequestBody List<DTO> dto, BindingResult errors) {
		if (((Errors) errors).hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		List<T> createdT = iGenericService.createAll(dto);
		
		return ResponseHandler.getResponse(createdT, HttpStatus.OK);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Object> update(@Valid @RequestBody DTO dto, BindingResult errors) {
		if (((Errors) errors).hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		T updatedT = iGenericService.update(dto);
		
		return ResponseHandler.getResponse(updatedT, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") ID id) {
		if (!iGenericService.checkExistIdOfT(id))
			return ResponseHandler.getResponse("Don't have any Area id", HttpStatus.BAD_REQUEST);
		
		iGenericService.deleteById(id);
		
		return ResponseHandler.getResponse("Delete Successfully", HttpStatus.OK);
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Object> findById(@PathVariable("id") ID id) {
		Optional<T> t = iGenericService.findById(id);

//		if (t.isEmpty())
		if (t.isEmpty() )

			return ResponseHandler.getResponse("There is no data.", HttpStatus.BAD_REQUEST);
			
		return ResponseHandler.getResponse(t, HttpStatus.OK);
	}
	
	

}
