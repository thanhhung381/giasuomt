package giasuomt.demo.person.model;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.commondata.util.Calendar;
import giasuomt.demo.commondata.util.DateTimeUtils;

import giasuomt.demo.educational.model.SubjectGroup;
import giasuomt.demo.job.model.Job;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.tags.model.TutorTag;
import giasuomt.demo.task.model.Application;
import giasuomt.demo.tutorReview.model.TutorReview;
import giasuomt.demo.user.model.User;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Invitation_Tutor")
@Getter
@Setter
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class TutorInvitation extends AbstractEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	private RegisterAndLearner registerAndLearner;

	private String learnerAndReqisterPhone;

	@ManyToOne(fetch = FetchType.LAZY)
	private Tutor tutor;

	private String note;
}
