package giasuomt.demo.person.dto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import giasuomt.demo.commondata.dto.SavePersonDto;
import giasuomt.demo.location.model.SaveRegisterAndLearnerAddressDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveRegisterAndLearnerDto extends SavePersonDto implements Serializable {

	//Arvatar
	private Long idAvatar;
	
	private List<SaveRegisterAndLearnerAddressDto> saveRegisterAndLearnerAddressDtos = new ArrayList<>();// không có trước đó

	// HIỆN ĐANG LÀ
	// Lưu Student
	private List<SaveStudentDto> saveStudentDtos = new LinkedList<>();

	// luu School Teacher
	private List<SaveSchoolerDto> saveSchoolerDtos = new LinkedList<>();

	// luu worker
	private List<SaveWorkerDto> saveWorkerDtos = new LinkedList<>();
	
	
	// TAGS
	private List<Long> registerAndLearnerTagIds = new LinkedList<>();
	
	
	private String registerAndLearnerAddressNotices;
	
	
	//RELATIONSHIP
	private List<SaveRegisterAndLearnerRelationshipDto> saveRegisterAndLearnerRelationshipDtosWith = new LinkedList<>();
	
}
