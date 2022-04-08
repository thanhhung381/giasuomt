package giasuomt.demo.person.dto;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
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
	private List<SaveStudentDto> saveStudentDtos = new LinkedList<>();

	// Lưu GraduatedStudent
	private List<SaveGraduatedStudentDto> saveGraduatedStudentDtos = new LinkedList<>();

	// luu Institution Teacher
	private List<SaveInstitutionTeacherDto> saveInstitutionTeacherDtos = new LinkedList<>();

	// luu School Teacher
	private List<SaveSchoolTeacherDto> saveSchoolTeacherDtos = new LinkedList<>();

	// luu worker
	private List<SaveWorkerDto> saveWorkerDtos = new LinkedList<>();

	// TAGS
	private List<String> tutorTagIds = new LinkedList<>();

	//Registered Subjects 


	
	

}
