package giasuomt.demo.learnerAndRegister.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

import giasuomt.demo.commondata.model.User;
import giasuomt.demo.institution.model.Institution;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.task.model.Task;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "learner_and_register")
public class LearnerAndRegister extends User {
//THÔNG TIN CÁ NHÂN	
	private String addNo;
	
	private String addSt;
	
	private String addNote;
	
	@ManyToOne
	@JoinColumn(name = "area_id")
	private Area area;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name = "learner_and_register_institution",
	                   joinColumns = @JoinColumn(name = "learner_and_register_id"),
	                   inverseJoinColumns = @JoinColumn(name = "institution_id"))
	Set<Institution> institutions;
	
	private String note;
	
	private String img;

//MỐI QUAN HỆ VỚI LEARNER/REGISTER khác
	@OneToMany(mappedBy = "learnerAndRegister", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<LearnerAndRegisterRelationship> learnerAndRegisterRelationships;
	
	@OneToMany(mappedBy = "relationshipWith", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<LearnerAndRegisterRelationship> inLearnerAndRegisterRelationships;

//LỚP ĐĂNG KÝ và LỚP HỌC
    @OneToMany(mappedBy = "register", fetch = FetchType.LAZY)
    @JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
    private Set<Task> registeredTasks;
    
    @ManyToMany(mappedBy = "learners", fetch = FetchType.LAZY)
    @JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
    private Set<Task> learnedTasks = new HashSet<>();
 

    
    
    
//Helper Methods để đảm bảo dữ liệu luôn thống nhất giữa các đối tượng (LearnerAndRegister và Area)
	//Hàm để add Area mới vào LearnerAndRegister -> Sau đó JPA sẽ tự add LearnerAndRegister này vào Area do đã thiết lập quan hệ
	public LearnerAndRegister setAreaId(Long areaId) {
		if(areaId != null) {
			Area area = new Area();
			area.setId(areaId);
			this.area = area; 
	//		area.getLearnerAndRegisters().add(this);
		}
		return this;
	} 
    //Hàm để add Relationship với Register cho Learners
	public LearnerAndRegister addLearnerAndRegisterRelationship(LearnerAndRegister addedLearnerAndRegister, String relationship) {
		if(addedLearnerAndRegister.getId() != null) {
			LearnerAndRegisterRelationship learnerAndRegisterRelationship = new LearnerAndRegisterRelationship();
			learnerAndRegisterRelationship.learnerAndRegister(this)
										  .relationship(relationship)
										  .relationshipWith(addedLearnerAndRegister);
			if(this.learnerAndRegisterRelationships == null) {
				this.learnerAndRegisterRelationships = new HashSet<>();
			}
			this.learnerAndRegisterRelationships.add(learnerAndRegisterRelationship);
		}
		return this;
	}
    
  
//getter,setter kiểu fluentAPI
	public LearnerAndRegister addNo(String addNo) {
		this.addNo = addNo;
		return this;
	}
	
	public LearnerAndRegister addSt(String addSt) {
		this.addSt = addSt;
		return this;
	}
	
	public LearnerAndRegister addNote(String addNote) {
		this.addNote = addNote;
		return this;
	}
	
	public LearnerAndRegister note(String note) {
		this.note = note;
		return this;
	}
    
}
