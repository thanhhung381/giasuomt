package giasuomt.demo.location.Validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import giasuomt.demo.location.Validator.CheckDuplicateAreaValidator;

@Constraint(validatedBy = CheckDuplicateAreaValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckDuplicateArea {
	public String message() default "This Area is exist!";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};
}
