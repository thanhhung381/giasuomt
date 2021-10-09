package giasuomt.demo.tutor.service;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.tutor.dto.SaveInstitutionTeacherDto;
import giasuomt.demo.tutor.model.InstitutionTeacher;

public interface IInstitutionTeacherService extends IGenericService<InstitutionTeacher, Long> {

	InstitutionTeacher create(SaveInstitutionTeacherDto dto);

	InstitutionTeacher update(SaveInstitutionTeacherDto dto);

	InstitutionTeacher save(SaveInstitutionTeacherDto dto, InstitutionTeacher institutionTeacher);

	void delete(Long id);

	

}
