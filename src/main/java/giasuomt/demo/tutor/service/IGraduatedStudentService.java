package giasuomt.demo.tutor.service;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.tutor.dto.CreateGraduatedStudentDto;
import giasuomt.demo.tutor.model.GraduatedStudent;
import giasuomt.demo.tutor.model.Student;

public interface IGraduatedStudentService  extends IGenericService<GraduatedStudent, Long> {

	GraduatedStudent save(CreateGraduatedStudentDto dto);

}
