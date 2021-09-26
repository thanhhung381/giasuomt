package giasuomt.demo.institution.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.tutor.model.InstitutionTeacher;
import giasuomt.demo.tutor.model.SchoolTeacher;

@Entity
@Table(name = "institution_type")
public class InstitutionType extends AbstractEntity {
	private String typeName;
	
	@ManyToMany(mappedBy="institutionTypes")
	@JsonIgnore
	private Set<Institution> institutions;
	
	
	@ManyToMany(mappedBy = "institutions", fetch = FetchType.LAZY)
	@JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
	private Set<SchoolTeacher> schoolTeacher = new HashSet<>();
	
	@OneToMany(mappedBy = "institutionType", fetch = FetchType.LAZY)
	@JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
	Set<InstitutionTeacher> institutionTeachers;

	//getter,setter
	
	public String getTypeName() {
		return typeName;
	}

	public Set<Institution> getInstitutions() {
		return institutions;
	}

	public void setInstitutions(Set<Institution> institutions) {
		this.institutions = institutions;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Set<InstitutionTeacher> getInstitutionTeachers() {
		return institutionTeachers;
	}

	public void setInstitutionTeachers(Set<InstitutionTeacher> institutionTeachers) {
		this.institutionTeachers = institutionTeachers;
	}
	
	
}
