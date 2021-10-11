package giasuomt.demo.person.service;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.person.dto.SaveStudentDto;
import giasuomt.demo.person.model.Student;

public interface IStudentService extends IGenericService<Student, Long>  {
	public void delete(Long id);

	public Student create(SaveStudentDto dto);

	public Student update(SaveStudentDto dto);

	Student save(SaveStudentDto dto, Student student);
}
