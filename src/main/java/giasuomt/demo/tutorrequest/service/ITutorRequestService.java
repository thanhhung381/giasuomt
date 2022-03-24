package giasuomt.demo.tutorrequest.service;

import giasuomt.demo.commondata.generic.GenericService;
import giasuomt.demo.commondata.generic.IGenericService;
import giasuomt.demo.tutorrequest.dto.SaveTutorRequestDto;
import giasuomt.demo.tutorrequest.model.TutorRequest;

public interface ITutorRequestService extends IGenericService<SaveTutorRequestDto, TutorRequest, Long>  {

}
