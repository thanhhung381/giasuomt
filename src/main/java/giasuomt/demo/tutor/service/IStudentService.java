package giasuomt.demo.tutor.service;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.location.dto.UpdateAreaDTO;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.tutor.dto.CreateStudentDto;
import giasuomt.demo.tutor.dto.UpdateStudentDto;
import giasuomt.demo.tutor.model.Student;

public interface IStudentService extends IGenericService<Student, Long>  {
	public Student save(CreateStudentDto dto);

	

	Student update(UpdateStudentDto dto, Long id);
}
