package giasuomt.demo.task.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import giasuomt.demo.educational.model.Subject;
import giasuomt.demo.task.model.Require;
import giasuomt.demo.task.model.TaskPlaceAddress;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveTaskDto {

	private Long id;
	
  //NƠI HỌC
	
	private String taskPlaceType; 
	
	private List<SaveTaskPlaceAddressDto> saveTaskPlaceAddressDtos=new ArrayList<>();//không có trước đó
	//MÔN HỌC
		//Trường nảy chỉ dùng cho API chỉnh sửa thông tin lớp, và API suggest (ko dùng cho API hiển thị thông tin lớp)
		
		private List<Long> idSubjects = new ArrayList<>();
		
		//Trường này dùng cho API hiển thị thông tin lớp (để ko cần phải query thêm bảng subjects)
		//Đây cũng là trường để lưu lại lịch sử nếu sau này nếu có chỉnh sửa database của bảng Subject
		private String subjectApperance; 
		
		private String subjectNote;
	
		//YÊU CẦU
		//Trường nảy chỉ dùng cho API chỉnh sửa thông tin lớp, và API suggest (ko dùng cho API hiển thị thông tin lớp)
		
		private List<Long> idRequires = new ArrayList<>();
		
		//Trường này dùng cho API hiển thị thông tin lớp (để ko cần phải query thêm bảng subjects)
		//Đây cũng là trường để lưu lại lịch sử nếu sau này nếu có chỉnh sửa database của bảng Subject
}
