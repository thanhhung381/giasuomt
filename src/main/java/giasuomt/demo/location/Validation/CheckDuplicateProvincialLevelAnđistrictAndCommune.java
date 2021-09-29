package giasuomt.demo.location.Validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import giasuomt.demo.location.Validator.CheckDuplicateProvincialLevelAnđistrictAndCommunevalidator;

@Constraint(validatedBy = CheckDuplicateProvincialLevelAnđistrictAndCommunevalidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckDuplicateProvincialLevelAnđistrictAndCommune {

	
	
	public  String getProvincialLevel() default "getProvincialLevel";
	
	public  String getDistrict() default "getDistrict";
	
	public  String getCommune() default "getCommune";
	
	public String message() default "Commune,District,ProvincialLevel,State can't be duplicate after updating";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default {};
}
