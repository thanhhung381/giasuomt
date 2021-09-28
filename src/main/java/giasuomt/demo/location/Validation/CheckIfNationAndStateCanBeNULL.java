package giasuomt.demo.location.Validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import giasuomt.demo.location.Validator.CheckIfNationAndStateCanBeNULLValidator;

@Constraint(validatedBy = CheckIfNationAndStateCanBeNULLValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckIfNationAndStateCanBeNULL {
	
	public String getNation() default "getNation";
	
	public  String getState() default "getState";
	
	public String message() default "State Can't be Null Because Nation is not Viet Nam";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default {};
	
}
