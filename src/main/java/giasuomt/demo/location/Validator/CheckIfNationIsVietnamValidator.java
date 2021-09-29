package giasuomt.demo.location.Validator;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.annotations.Nationalized;

import giasuomt.demo.location.Validation.CheckIfNationIsVietnam;

public class CheckIfNationIsVietnamValidator implements ConstraintValidator<CheckIfNationIsVietnam, Object> {

	
	private String message;
	private String getNation;
	private String getProvincialLevel;
	private String getDistrict;
	private String getCommune;
	private String getState;
	
	
	@Override
	public void initialize(CheckIfNationIsVietnam constraintAnnotation) {
		// TODO Auto-generated method stub
		this.message=constraintAnnotation.message();
		this.getNation=constraintAnnotation.getNation();
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
			String nation=(String)value.getClass().getMethod(getNation).invoke(value);
			
			switch (nation) 
			{
				case "Việt Nam":
				{
					
					if(district!=null && provinceLevel!=null && commune!=null )
						return true;
					
					
					context.buildConstraintViolationWithTemplate(message)
					.addConstraintViolation()
					.disableDefaultConstraintViolation();
				
				
					break;
				}
				default:
				{
					if(state!=null)
						return true;
					context.buildConstraintViolationWithTemplate(message)
					.addConstraintViolation()
					.disableDefaultConstraintViolation();
				
						
					break;
				}
				
			}
			
			
			
			
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
