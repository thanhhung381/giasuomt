package giasuomt.demo.institution.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import giasuomt.demo.commondata.model.AbstractEntity;
import giasuomt.demo.learnerAndRegister.model.LearnerAndRegister;
import giasuomt.demo.tutor.model.GraduatedStudent;
import giasuomt.demo.tutor.model.Student;

@Entity
@Table(name = "institution")
public class Institution extends AbstractEntity {
	private String name;
	
	private String nameAbbr;
	
	private String code;
	
	@ManyToMany(cascade = {CascadeType.MERGE})
	@JoinTable(name = "institution_institution_type",
			   joinColumns = @JoinColumn(name = "institution_id"),
			   inverseJoinColumns = @JoinColumn(name = "institution_type_id"))
	private Set<InstitutionType> institutionTypes;
	
	@OneToMany(mappedBy = "institution", fetch = FetchType.EAGER)
	private Set<InstitutionAddress> addresses;
	
	@OneToMany(mappedBy = "institution", fetch = FetchType.EAGER)
	@JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
	Set<Major> majors;	
	
	
	@OneToMany(mappedBy = "institution", fetch = FetchType.LAZY)
	@JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
	Set<Student> students;

	@OneToMany(mappedBy = "institution", fetch = FetchType.LAZY)
	@JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
	Set<GraduatedStudent> graduatedStudents;
	
	@ManyToMany(mappedBy = "institutions", fetch = FetchType.LAZY)
	@JsonIgnore  //Để JSP ignore cột này khi truy vấn, để ko bị lập vô tận
	private Set<LearnerAndRegister> learnerAndRegisters = new HashSet<>();

	
	//getter,setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameAbbr() {
		return nameAbbr;
	}

	public void setNameAbbr(String nameAbbr) {
		this.nameAbbr = nameAbbr;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<InstitutionType> getInstitutionTypes() {
		return institutionTypes;
	}

	public void setInstitutionTypes(Set<InstitutionType> institutionTypes) {
		this.institutionTypes = institutionTypes;
	}

	public Set<InstitutionAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<InstitutionAddress> addresses) {
		this.addresses = addresses;
	}

	public Set<Major> getMajors() {
		return majors;
	}

	public void setMajors(Set<Major> majors) {
		this.majors = majors;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<GraduatedStudent> getGraduatedStudents() {
		return graduatedStudents;
	}

	public void setGraduatedStudents(Set<GraduatedStudent> graduatedStudents) {
		this.graduatedStudents = graduatedStudents;
	}
	
	
}
