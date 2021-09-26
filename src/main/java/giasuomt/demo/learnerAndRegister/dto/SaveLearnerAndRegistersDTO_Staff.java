package giasuomt.demo.learnerAndRegister.dto;

import java.util.Set;

public class SaveLearnerAndRegistersDTO_Staff {
	private SaveLearnerAndRegisterDTO_Staff register;
	
	private Set<SaveLearnerAndRegisterDTO_Staff> learners;

	
	//getter,setter
	public SaveLearnerAndRegisterDTO_Staff getRegister() {
		return register;
	}

	public void setRegister(SaveLearnerAndRegisterDTO_Staff register) {
		this.register = register;
	}

	public Set<SaveLearnerAndRegisterDTO_Staff> getLearners() {
		return learners;
	}

	public void setLearners(Set<SaveLearnerAndRegisterDTO_Staff> learners) {
		this.learners = learners;
	}
	
	
}
