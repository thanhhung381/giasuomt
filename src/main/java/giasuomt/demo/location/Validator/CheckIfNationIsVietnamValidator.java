package giasuomt.demo.location.Validator;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import giasuomt.demo.location.Validation.CheckIfNationIsVietnam;

public class CheckIfNationIsVietnamValidator implements ConstraintValidator<CheckIfNationIsVietnam, Object> {

	private String message;

	@Override
	public void initialize(CheckIfNationIsVietnam constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		try {
			String district = (String) value.getClass().getMethod("getDistrict").invoke(value);
			String provinceLevel = (String) value.getClass().getMethod("getProvincialLevel").invoke(value);
			String commune = (String) value.getClass().getMethod("getCommune").invoke(value);
			String nation = (String) value.getClass().getMethod("getNation").invoke(value);

			if (nation.equals("Việt Nam") ){
				if (district != null && !district.trim().isEmpty() && provinceLevel != null
						&& !provinceLevel.trim().isEmpty() && commune != null && !commune.trim().isEmpty()) {
					context.buildConstraintViolationWithTemplate(message)
						   .addConstraintViolation()
						   .disableDefaultConstraintViolation();
					return true;
				}
			}

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		return false;
	}

}
