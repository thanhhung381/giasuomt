package giasuomt.demo.tutor.service;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.tutor.dto.SaveSchoolTeacherDto;
import giasuomt.demo.tutor.model.SchoolTeacher;

public interface ISchoolTeacherService extends IGenericService<SchoolTeacher, Long> {

	SchoolTeacher save(SaveSchoolTeacherDto dto);

}
