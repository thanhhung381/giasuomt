package giasuomt.demo.educational.service;

import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.educational.dto.SaveSchoolDto;
import giasuomt.demo.educational.model.School;
import giasuomt.demo.person.model.Schooler;

public interface ISchoolService extends IGenericService<SaveSchoolDto, School, Long> {

}
