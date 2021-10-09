package giasuomt.demo.tutor.service;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.tutor.dto.SaveGraduatedStudentDto;
import giasuomt.demo.tutor.model.GraduatedStudent;

public interface IGraduatedStudentService  extends IGenericService<GraduatedStudent, Long> {

	GraduatedStudent create(SaveGraduatedStudentDto dto);

	GraduatedStudent update(SaveGraduatedStudentDto dto);

	GraduatedStudent save(SaveGraduatedStudentDto dto, GraduatedStudent graduatedStudent);

	void delete(Long id);

}
