package giasuomt.demo.location.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import giasuomt.demo.location.Validation.ExistIdArea;
import giasuomt.demo.location.service.IAreaService;

public class ExistIdAreaValidator implements ConstraintValidator<ExistIdArea, Long> {

	
	@Autowired
	private IAreaService service;
	
	private String message;
	
	@Override
	public void initialize(ExistIdArea constraintAnnotation) {
		// TODO Auto-generated method stub
		this.message=constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		
		boolean checkExist=service.checkExistIdofArea(value);
		if(checkExist)
	
			return true
;
		context.buildConstraintViolationWithTemplate(message)
		.addConstraintViolation()
		.disableDefaultConstraintViolation();
		
		return false;
	}

}
