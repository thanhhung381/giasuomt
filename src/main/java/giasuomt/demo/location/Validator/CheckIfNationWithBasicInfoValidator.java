package giasuomt.demo.location.Validator;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import giasuomt.demo.location.Validation.CheckIfNationWithBasicInfo;

public class CheckIfNationWithBasicInfoValidator implements ConstraintValidator<CheckIfNationWithBasicInfo, Object> {

	
	private String getMessage;
	private String getNation;
	private String getProvincialLevel;
	private String getDistrict;
	private String getCommune;
	
	
	@Override
	public void initialize(CheckIfNationWithBasicInfo constraintAnnotation) {
		// TODO Auto-generated method stub
		this.getMessage=constraintAnnotation.message();
		this.getNation=constraintAnnotation.getNation();
		this.getDistrict=constraintAnnotation.getDistrict();
		this.getCommune=constraintAnnotation.getCommune();
		this.getProvincialLevel=constraintAnnotation.getProvincialLevel();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		
		
		 try {
			String nation=(String)value.getClass().getMethod(getNation).invoke(value);
			
			String provincialLevel=(String)value.getClass().getMethod(getProvincialLevel).invoke(value);
			 
			 String district=(String)value.getClass().getMethod(getDistrict).invoke(value);
			 String commune=(String)value.getClass().getMethod(getCommune).invoke(value);
			 
			 
			 if((nation.equals("Việt Nam") && !provincialLevel.equals("") && !district.equals("") && !commune.equals("")))
				 return true;
			 
			 context.buildConstraintViolationWithTemplate(getMessage).addConstraintViolation().disableDefaultConstraintViolation();
			 
			 
			 
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return false;
	}

}
