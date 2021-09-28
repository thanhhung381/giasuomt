package giasuomt.demo.location.Validator;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import giasuomt.demo.location.Validation.CheckIfNationAndStateCanBeNULL;

public class CheckIfNationAndStateCanBeNULLValidator implements ConstraintValidator<CheckIfNationAndStateCanBeNULL, Object> {

	private String message;
	private String getNation;
	private String getState;
	
	@Override
	public void initialize(CheckIfNationAndStateCanBeNULL constraintAnnotation) {
		// TODO Auto-generated method stub
		this.getNation=constraintAnnotation.getNation();
		this.getState=constraintAnnotation.getState();
		this.message=constraintAnnotation.message();
	}
	
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		
		try {
			String nation=(String)value.getClass().getMethod(getNation).invoke(value);
			String state=(String)value.getClass().getMethod(getState).invoke(value);
			//String state=(String)value.getClass().getMethod(getState).invoke(value);
			if((nation.equals("Việt Nam")) ||( !nation.equals("Việt Nam") && !state.equals(" ")) )
				return true;
			
			context.buildConstraintViolationWithTemplate(message)
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
