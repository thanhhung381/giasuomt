package giasuomt.demo.tutor.service;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.tutor.dto.SaveInstitutionTeacherDto;
import giasuomt.demo.tutor.model.InstitutionTeacher;

public interface IInsitutionTeacherService extends IGenericService<InstitutionTeacher, Long> {

	InstitutionTeacher save(SaveInstitutionTeacherDto dto);

}
