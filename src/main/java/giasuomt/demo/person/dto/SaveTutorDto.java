package giasuomt.demo.person.dto;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import giasuomt.demo.commondata.dto.SavePersonDto;
import giasuomt.demo.commondata.util.Voice;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveTutorDto extends SavePersonDto implements Serializable{

	
	//Arvatar
	private Long idAvatar;

	// THÔNG TIN CÁ NHÂN
	private String tempAddNo;

	private String tempAddSt;

	private String tempAddNote;

	private String tempAreaId;

	private String perAddNo;

	private String perAddSt;

	private String perAddNote;

	private String perAreaId;

	private String infoImgs;

	// NĂNG LỰC:
	private List<Voice> voices=new LinkedList<>();

	private String tutorNotices;

	private String advantageNote;

	// ĐĂNG KÝ NHẬN LỚP

	// NHẬN LỚP

	// VỊ TRÍ TƯƠNG ĐỐI CỦA GIA SƯ
	private String xRelCoo;

	private String yRelCoo;

	private String relAreaId;

	// HIỆN ĐANG LÀ
	// Lưu Student
	private Set<SaveStudentDto> saveStudentDtos = new HashSet<>();

	// Lưu GraduatedStudent
	private Set<SaveGraduatedStudentDto> saveGraduatedStudentDtos = new HashSet<>();

	// luu Institution Teacher
	private Set<SaveInstitutionTeacherDto> saveInstitutionTeacherDtos = new HashSet<>();

	// luu School Teacher
	private Set<SaveSchoolTeacherDto> saveSchoolTeacherDtos = new HashSet<>();

	// luu worker
	private Set<SaveWorkerDto> saveWorkerDtos = new HashSet<>();

	// TAGS
	private Set<String> tutorTagIds = new HashSet<>();

	//Registered Subjects 


	
	

}
