package giasuomt.demo.location.Validation;

import java.lang.annotation.ElementType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


import giasuomt.demo.location.Validator.ExistIdAreaValidator;

@Constraint(validatedBy = ExistIdAreaValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistIdArea {

	public String message() default "Id of Area is not Exist";

	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default {};
}
