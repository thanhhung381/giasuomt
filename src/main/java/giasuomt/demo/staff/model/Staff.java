package giasuomt.demo.staff.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.websocket.OnError;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import giasuomt.demo.commondata.model.Person;
import giasuomt.demo.location.model.Area;
import giasuomt.demo.user.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Staff")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class Staff extends Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String avatar;

	@OneToOne(mappedBy = "staff")
	@JsonIgnore
	private User user;

}
