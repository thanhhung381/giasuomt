package giasuomt.demo.location.Validator;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import giasuomt.demo.location.Validation.CheckDuplicateProvincialLevelAnđistrictAndCommune;

public class CheckDuplicateProvincialLevelAnđistrictAndCommunevalidator implements ConstraintValidator<CheckDuplicateProvincialLevelAnđistrictAndCommune, Object> {

	
	private String getMessage;
	private String getProvincialLevel;
	private String getDistrict;
	private String getCommune;
	private String getState;
	
	@Override
	public void initialize(CheckDuplicateProvincialLevelAnđistrictAndCommune constraintAnnotation) {
		// TODO Auto-generated method stub
		this.getMessage=constraintAnnotation.message();
		this.getProvincialLevel=constraintAnnotation.getProvincialLevel();
		this.getDistrict=constraintAnnotation.getDistrict();
		this.getState=constraintAnnotation.getState();
		this.getCommune=constraintAnnotation.getCommune();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		
		try {
			String district=(String)value.getClass().getMethod(getDistrict).invoke(value);
			String provinceLevel=(String)value.getClass().getMethod(getProvincialLevel).invoke(value);
			String commune=(String)value.getClass().getMethod(getCommune).invoke(value);
			String state=(String)value.getClass().getMethod(getState).invoke(value);
			
			if(!district.equals(state) && !state.equals(commune) && !state.equals(getProvincialLevel) 
					&& !district.equals(provinceLevel) && !district.equals(commune) && !commune.equals(provinceLevel))
				
				return true;
			
			context.buildConstraintViolationWithTemplate(getMessage)
			.addConstraintViolation()
			.disableDefaultConstraintViolation();
			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return false;
	}

}
