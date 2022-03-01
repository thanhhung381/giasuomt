package giasuomt.demo.user.dto;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import giasuomt.demo.person.model.RegisterAndLearner;
import giasuomt.demo.person.model.Tutor;
import giasuomt.demo.role.model.Role;
import lombok.Setter;
import lombok.Getter;
@Getter
@Setter
public class ResponseUser {
	
  private String username;
	

	
	private String email;
	
	private String phones;
	
	
	private Tutor tutor;
	
	
	private RegisterAndLearner registerAndLearner;
	

	
}
