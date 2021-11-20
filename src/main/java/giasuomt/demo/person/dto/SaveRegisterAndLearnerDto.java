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
	private String vocative;
	
	//Arvatar
	private Long idAvatar;
	
	private List<SaveRegisterAndLearnerAddressDto> registerAndLearnerAddresses = new ArrayList<>();// không có trước đó

	// HIỆN ĐANG LÀ
	// Lưu Student
	private List<SaveStudentDto> students = new LinkedList<>();

	// luu School Teacher
	private List<SaveSchoolerDto> schoolers = new LinkedList<>();

	// luu worker
	private List<SaveWorkerDto> workers = new LinkedList<>();
	
	
	// TAGS
	private List<Long> registerAndLearnerTagIds = new LinkedList<>();
	

	
	
	//RELATIONSHIP
	private List<SaveRegisterAndLearnerRelationshipDto> registerAndLearnerRelationships = new LinkedList<>();
	
}
