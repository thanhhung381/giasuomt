package giasuomt.demo.location.Validation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import giasuomt.demo.location.Validator.CheckIfNationIsVietnamValidator;


@Constraint(validatedBy = CheckIfNationIsVietnamValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckIfNationIsVietnam {
	public String message() default "Because nation is Viet Nam, Commune,District,ProvincialLevel can't be null and blank ";

	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default {};
}
