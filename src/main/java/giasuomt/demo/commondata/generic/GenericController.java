package giasuomt.demo.commondata.generic;
import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class GenericController<DTO, T extends AbstractEntity, ID, BindingResult> {
	@Autowired
	private IGenericService<DTO, T, ID> iGenericService;
	

	@GetMapping("/findAll")
	public ResponseEntity<Object> findall() {
		List<T> ts = iGenericService.findAll();
		if (ts==null)
			return ResponseHandler.getResponse("There is no data.", HttpStatus.OK);
			
		return ResponseHandler.getResponse(ts, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Object> create(@Valid @RequestBody DTO dto, BindingResult errors) {
		if (((Errors) errors).hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		T createdT = iGenericService.create(dto);
		
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
		if (t==null)
			return ResponseHandler.getResponse("There is no data.", HttpStatus.OK);
			
		return ResponseHandler.getResponse(t, HttpStatus.OK);
	}

}
