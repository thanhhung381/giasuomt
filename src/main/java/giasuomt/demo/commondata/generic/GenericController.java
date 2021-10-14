package giasuomt.demo.commondata.generic;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.commondata.responseHandler.ResponseHandler;
import giasuomt.demo.educational.dto.SaveSubjectDto;
import giasuomt.demo.educational.model.Subject;

public abstract class GenericController<Dto, ID extends Long, B extends BindingResult> {

	public ResponseEntity<Object> findall() {

		return null;
	}

	public ResponseEntity<Object> create(@Valid @RequestBody Dto dto, B e) {

		return null;
	}

	public ResponseEntity<Object> update(@Valid @RequestBody Dto dtoB, B e) {

		return null;
	}

	public ResponseEntity<Object> delete(ID id) {

		return null;
	}

}
