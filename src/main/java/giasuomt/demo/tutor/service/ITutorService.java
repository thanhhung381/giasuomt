package giasuomt.demo.tutor.service;

import javax.validation.Valid;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.tutor.dto.CreateTutorDTO;
import giasuomt.demo.tutor.model.Tutor;

public interface ITutorService extends IGenericService<Tutor, Long>{

	Tutor saveTutor(@Valid CreateTutorDTO dto);

}
