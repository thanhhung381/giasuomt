package giasuomt.demo.tutor.service;

import javax.validation.Valid;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.institution.model.Institution;
import giasuomt.demo.tutor.dto.CreateInstitutionDTO;

public interface IInstitutionService extends IGenericService<Institution, Long> {

	Institution save(@Valid CreateInstitutionDTO dto);



}
