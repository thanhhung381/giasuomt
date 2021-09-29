package giasuomt.demo.location.Validator;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

<<<<<<< Updated upstream:src/main/java/giasuomt/demo/location/Validator/CheckDuplicateProvincialLevelAnđistrictAndCommunevalidator.java
import giasuomt.demo.location.Validation.CheckDuplicateProvincialLevelAnđistrictAndCommune;
=======
import org.springframework.beans.factory.annotation.Autowired;

import giasuomt.demo.location.Validation.CheckDuplicateArea;
import giasuomt.demo.location.service.IAreaService;
>>>>>>> Stashed changes:src/main/java/giasuomt/demo/location/Validator/CheckDuplicateAreaValidator.java

public class CheckDuplicateAreaValidator implements ConstraintValidator<CheckDuplicateArea, Object> {

	private String getMessage;
	private String getProvincialLevel;
	private String getDistrict;
	private String getCommune;
<<<<<<< Updated upstream:src/main/java/giasuomt/demo/location/Validator/CheckDuplicateProvincialLevelAnđistrictAndCommunevalidator.java
	private String getState;
	
=======

	@Autowired
	private IAreaService service;

>>>>>>> Stashed changes:src/main/java/giasuomt/demo/location/Validator/CheckDuplicateAreaValidator.java
	@Override
	public void initialize(CheckDuplicateArea constraintAnnotation) {
		// TODO Auto-generated method stub
<<<<<<< Updated upstream:src/main/java/giasuomt/demo/location/Validator/CheckDuplicateProvincialLevelAnđistrictAndCommunevalidator.java
		this.getMessage=constraintAnnotation.message();
		this.getProvincialLevel=constraintAnnotation.getProvincialLevel();
		this.getDistrict=constraintAnnotation.getDistrict();
		this.getState=constraintAnnotation.getState();
		this.getCommune=constraintAnnotation.getCommune();
=======
		this.getMessage = constraintAnnotation.message();
		this.getProvincialLevel = constraintAnnotation.getProvincialLevel();
		this.getDistrict = constraintAnnotation.getDistrict();
		this.getCommune = constraintAnnotation.getCommune();
>>>>>>> Stashed changes:src/main/java/giasuomt/demo/location/Validator/CheckDuplicateAreaValidator.java
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub

		try {
<<<<<<< Updated upstream:src/main/java/giasuomt/demo/location/Validator/CheckDuplicateProvincialLevelAnđistrictAndCommunevalidator.java
			String district=(String)value.getClass().getMethod(getDistrict).invoke(value);
			String provinceLevel=(String)value.getClass().getMethod(getProvincialLevel).invoke(value);
			String commune=(String)value.getClass().getMethod(getCommune).invoke(value);
			String state=(String)value.getClass().getMethod(getState).invoke(value);
			
			if(!district.equals(state) && !state.equals(commune) && !state.equals(getProvincialLevel) 
					&& !district.equals(provinceLevel) && !district.equals(commune) && !commune.equals(provinceLevel))
				
=======
			String district = (String) value.getClass().getMethod(getDistrict).invoke(value);
			String provinceLevel = (String) value.getClass().getMethod(getProvincialLevel).invoke(value);
			String commune = (String) value.getClass().getMethod(getCommune).invoke(value);

			if (!service.checkExistCommune(commune) || !service.checkExistDistrict(district)
					|| !service.checkExistProvincialLevel(provinceLevel))

>>>>>>> Stashed changes:src/main/java/giasuomt/demo/location/Validator/CheckDuplicateAreaValidator.java
				return true;

			context.buildConstraintViolationWithTemplate(getMessage).addConstraintViolation()
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
