package giasuomt.demo.person.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import giasuomt.demo.commondata.dto.SavePersonDto;
import giasuomt.demo.commondata.util.DateTimeUtils;
import giasuomt.demo.commondata.util.HienDangLa;
import giasuomt.demo.commondata.util.Voice;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveTutorDto extends SavePersonDto implements Serializable {

	// Arvatar


	// THÔNG TIN CÁ NHÂN


	private String tutorAddressAreaId;
	
	private  String tutorAddress;






	// NĂNG LỰC:
	private List<Voice> voices = new LinkedList<>();

	private String tutorNotices;

	private String advantageNote;

	// ĐĂNG KÝ NHẬN LỚP

	// NHẬN LỚP

	// VỊ TRÍ TƯƠNG ĐỐI CỦA GIA SƯ
	private String xRelCoo;

	private String yRelCoo;

	private String relAreaId;

	// HIỆN ĐANG LÀ
	
	private Set<HienDangLa>  hienDangLa=new HashSet<>();
	
	
	private String studyingInsitution;
	
	private String teachingInstitution;
	
	private String major;

	// TAGS
	private Set<String> tutorTagIds = new HashSet<>();
	
	private Integer  nowLevel;

	// Registered Subjects

}
