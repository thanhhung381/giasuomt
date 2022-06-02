package giasuomt.demo.person.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import giasuomt.demo.commondata.generic.GenericController;
import giasuomt.demo.person.dto.SaveTutorInterestDto;
import giasuomt.demo.person.dto.SaveTutorInvitationDto;
import giasuomt.demo.person.model.TutorInterest;
import giasuomt.demo.person.model.TutorInvitation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/tutorInvitation")
@AllArgsConstructor
public class TutorInvitationController extends GenericController<SaveTutorInvitationDto, TutorInvitation, Long> {

}
