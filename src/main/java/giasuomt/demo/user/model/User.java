package giasuomt.demo.user.model;

import java.time.LocalDateTime;


import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import giasuomt.demo.comment.model.TaskComment;
import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.commondata.model.Person;
import giasuomt.demo.commondata.util.DateUtils;

import giasuomt.demo.finance.util.AmoutPerTime;
import giasuomt.demo.finance.util.PercentageOfMoney;
import giasuomt.demo.finance.util.TypeOfFee;
import giasuomt.demo.finance.util.UnitOfMoney;
import giasuomt.demo.job.model.Job;
import giasuomt.demo.location.model.TaskPlaceAddress;
import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.role.model.Role;
import giasuomt.demo.staff.model.Staff;
import giasuomt.demo.task.model.Application;
import giasuomt.demo.task.model.Registration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "table_user")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class User extends AbstractEntity {
	
	private String avatar;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private String phones;
	
	@OneToOne
	private Tutor tutor;
	
	@OneToOne
	private RegisterAndLearner registerAndLearner;
	
	@OneToOne
	private Staff staff;
	
	
	
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER)
	@JoinTable(name = "User_Role_GSOMT",joinColumns = @JoinColumn(name="id_User"),inverseJoinColumns =  @JoinColumn(name="id_Role"))
	private List<Role> roles=new LinkedList<>();
	


	
	public void removeRole(Role role)
	{
		this.roles.remove(role);
	}




	
	
}
