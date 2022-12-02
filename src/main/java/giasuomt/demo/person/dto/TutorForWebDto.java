package giasuomt.demo.person.dto;

import java.util.HashSet;
import java.util.Set;

import giasuomt.demo.educational.model.SubjectGroup;
import giasuomt.demo.location.model.Area;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TutorForWebDto {
	private Long id;
	private String fullName;
	private String avatar;
	private String gender;
	private String voices;
	private Set<Area> relArea = new HashSet<>();
	private String advantageNote;
	private Double averageStarNumbers;
	private int tutorReviewNumbers; // đếm tổng các TutorReview
	private int jobNumbers; // : đếm tổng các Job
	private String hienDangLa;
	private Set<SubjectGroup> subjectGroupMaybes = new HashSet<>();

}
