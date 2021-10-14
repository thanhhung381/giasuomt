package giasuomt.demo.educational.service;

import java.util.List;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.educational.dto.SaveSubjectDto;
import giasuomt.demo.educational.dto.SaveSubjectGroupDto;
import giasuomt.demo.educational.model.Subject;
import giasuomt.demo.educational.model.SubjectGroup;

public interface ISubjectService  {

	List<Subject> findall();

	public Subject create(SaveSubjectDto dto);

	public Subject save(SaveSubjectDto dto, Subject subject);

	public void delete(Long id);

	public boolean checkExistIdofSubject(Long id);

	public Subject update(SaveSubjectDto dto);

}
