package giasuomt.demo.tutor.service;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.tutor.dto.SaveStudentDto;
import giasuomt.demo.tutor.model.Student;

public interface IStudentService extends IGenericService<Student, Long>  {
	public void delete(Long id);

	public Student create(SaveStudentDto dto);

	public Student update(SaveStudentDto dto);

	Student save(SaveStudentDto dto, Student student);
}
