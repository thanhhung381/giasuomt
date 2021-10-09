package giasuomt.demo.tutor.service;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.tutor.dto.SaveSchoolTeacherDto;
import giasuomt.demo.tutor.model.SchoolTeacher;

public interface ISchoolTeacherService extends IGenericService<SchoolTeacher, Long> {

	SchoolTeacher create(SaveSchoolTeacherDto dto);

	SchoolTeacher update(SaveSchoolTeacherDto dto);

	SchoolTeacher save(SaveSchoolTeacherDto dto, SchoolTeacher schoolTeacher);

	void delete(Long id);

}
