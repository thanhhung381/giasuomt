package giasuomt.demo.person.dto;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import giasuomt.demo.commondata.util.Gender;
import giasuomt.demo.commondata.util.HienDangLa;
import giasuomt.demo.commondata.util.Voice;
import giasuomt.demo.educational.model.SubjectGroup;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.tags.model.TutorTag;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TutorForWebDto {
	private Long id;
	private String fullName;
	private String avatar;
	private Gender gender;
	private Set<Voice> voices = new HashSet<>();
	private Area relArea;

	
	private String advantageNote;
	private Set<TutorTag> tutorTags = new HashSet<>();
	private Double averageStarNumbers;
	private int tutorReviewNumbers; // đếm tổng các TutorReview
	private int jobNumbers; // : đếm tổng các Job

	private Set<HienDangLa> hienDangLa=new HashSet<>();
	
	private String studyingInsitution;
	
	private String teachingInstitution;
	
	private String major;
	
	
	private Set<SubjectGroup> subjectGroupMaybes = new HashSet<>();

	private Set<SubjectGroup> subjectGroupSures = new HashSet<>();

}
