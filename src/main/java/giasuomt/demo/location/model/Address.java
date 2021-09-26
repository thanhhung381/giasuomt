package giasuomt.demo.location.model;

import javax.persistence.MappedSuperclass;

import giasuomt.demo.commondata.model.AbstractEntity;


@MappedSuperclass
public class Address extends AbstractEntity {
	private String exactAddNo;
	
	private String exactAddSt;
	
	private String exactAddNote;
	
	private String exactXCoo;
	
	private String exactYCoo;
	
	
	private String relAddNo;
	
	private String relAddSt;	
	
	private String relAddNote;
	
	private String relXCoo;
	
	private String relYCoo;

	
	//getter,setter kiểu fluentAPI
	public Address exactAddNo(String exactAddNo) {   
		this.exactAddNo = exactAddNo;
		return this;
	}
	public Address exactAddSt(String exactAddSt) {   
		this.exactAddSt = exactAddSt;
		return this;
	}
	public Address exactAddNote(String exactAddNote) {   
		this.exactAddNote = exactAddNote;
		return this;
	}
	public Address exactXCoo(String exactXCoo) {   
		this.exactXCoo = exactXCoo;
		return this;
	}
	public Address exactYCoo(String exactYCoo) {   
		this.exactYCoo = exactYCoo;
		return this;
	}
	public Address relAddNo(String relAddNo) {   
		this.relAddNo = relAddNo;
		return this;
	}
	public Address relAddSt(String relAddSt) {   
		this.relAddSt = relAddSt;
		return this;
	}
	public Address relAddNote(String relAddNote) {   
		this.relAddNote = relAddNote;
		return this;
	}
	public Address relXCoo(String relXCoo) {   
		this.relXCoo = relXCoo;
		return this;
	}
	public Address relYCoo(String relYCoo) {   
		this.relYCoo = relYCoo;
		return this;
	}
	
	//getter,setter
	public String getRelAddNote() {
		return relAddNote;
	}

	public void setRelAddNote(String relAddNote) {
		this.relAddNote = relAddNote;
	}

	public String getExactAddNo() {
		return exactAddNo;
	}

	public void setExactAddNo(String exactAddNo) {
		this.exactAddNo = exactAddNo;
	}

	public String getExactAddSt() {
		return exactAddSt;
	}

	public void setExactAddSt(String exactAddSt) {
		this.exactAddSt = exactAddSt;
	}

	public String getExactAddNote() {
		return exactAddNote;
	}

	public void setExactAddNote(String exactAddNote) {
		this.exactAddNote = exactAddNote;
	}

	public String getExactXCoo() {
		return exactXCoo;
	}

	public void setExactXCoo(String exactXCoo) {
		this.exactXCoo = exactXCoo;
	}

	public String getExactYCoo() {
		return exactYCoo;
	}

	public void setExactYCoo(String exactYCoo) {
		this.exactYCoo = exactYCoo;
	}

	public String getRelAddNo() {
		return relAddNo;
	}

	public void setRelAddNo(String relAddNo) {
		this.relAddNo = relAddNo;
	}

	public String getRelAddSt() {
		return relAddSt;
	}

	public void setRelAddSt(String relAddSt) {
		this.relAddSt = relAddSt;
	}

	public String getRelXCoo() {
		return relXCoo;
	}

	public void setRelXCoo(String relXCoo) {
		this.relXCoo = relXCoo;
	}

	public String getRelYCoo() {
		return relYCoo;
	}

	public void setRelYCoo(String relYCoo) {
		this.relYCoo = relYCoo;
	}
	
	
}
