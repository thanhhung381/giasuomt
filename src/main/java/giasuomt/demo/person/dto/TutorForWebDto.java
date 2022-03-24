package giasuomt.demo.person.dto;

import java.util.LinkedList;
import java.util.List;

import giasuomt.demo.location.model.Area;
import giasuomt.demo.tags.model.TutorTag;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TutorForWebDto {
	private Long id;
   private String fullName ;
    private String avatar;
    private String gender ;
    private String voices;
    private Area relArea;
    private String subjectGroupSure;
    private String subjectGroupMaybe;
    private String advantageNote;
    private List<TutorTag> tutorTags = new LinkedList<>();
    private Double averageStarNumbers ;
    private int tutorReviewNumbers; // đếm tổng các TutorReview
    private int  jobNumbers; // : đếm tổng các Job
}
