package giasuomt.demo.user.model;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
import giasuomt.demo.educational.model.Subject;
import giasuomt.demo.finance.util.AmoutPerTime;
import giasuomt.demo.finance.util.PercentageOfMoney;
import giasuomt.demo.finance.util.TypeOfFee;
import giasuomt.demo.finance.util.UnitOfMoney;
import giasuomt.demo.job.model.Job;
import giasuomt.demo.location.model.TaskPlaceAddress;
import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.role.model.Role;
import giasuomt.demo.task.model.Application;
import giasuomt.demo.task.model.Registration;
import giasuomt.demo.task.model.Require;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "table_user")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class User extends AbstractEntity {
	
	private String username;
	
	private String password;
	
	private String email;
	
	private String phones;
	
	@OneToOne(mappedBy = "user")
	private Tutor tutor;
	
	@OneToOne(mappedBy = "user")
	private RegisterAndLearner registerAndLearner;
	
	
	@ManyToMany(mappedBy = "users",fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Role> roles=new LinkedList<>();
	
	

	
}
