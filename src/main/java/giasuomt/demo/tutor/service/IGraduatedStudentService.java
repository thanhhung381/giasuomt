package giasuomt.demo.tutor.service;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.tutor.dto.SaveGraduatedStudentDto;
import giasuomt.demo.tutor.model.GraduatedStudent;

public interface IGraduatedStudentService  extends IGenericService<GraduatedStudent, Long> {

	GraduatedStudent save(SaveGraduatedStudentDto dto);

}
