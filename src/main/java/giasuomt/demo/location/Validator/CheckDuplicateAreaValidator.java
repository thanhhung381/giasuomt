package giasuomt.demo.location.Validator;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import giasuomt.demo.location.Validation.CheckDuplicateArea;
import giasuomt.demo.location.service.IAreaService;

public class CheckDuplicateAreaValidator implements ConstraintValidator<CheckDuplicateArea, Object> {

	private String getMessage;

	@Autowired
	private IAreaService service;

	@Override
	public void initialize(CheckDuplicateArea constraintAnnotation) {
		this.getMessage = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub

		try {
			String district = (String) value.getClass().getMethod("getDistrict").invoke(value);
			String provinceLevel = (String) value.getClass().getMethod("getProvincialLevel").invoke(value);
			String commune = (String) value.getClass().getMethod("getCommune").invoke(value);

			if (!service.checkExistCommune(commune) || !service.checkExistDistrict(district)
					|| !service.checkExistProvincialLevel(provinceLevel))
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
