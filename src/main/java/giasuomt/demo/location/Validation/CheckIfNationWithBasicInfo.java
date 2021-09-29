package giasuomt.demo.location.Validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

<<<<<<< Updated upstream:src/main/java/giasuomt/demo/location/Validation/CheckIfNationWithBasicInfo.java
import giasuomt.demo.location.Validator.CheckIfNationAndStateCanBeNULLValidator;
import giasuomt.demo.location.Validator.CheckIfNationWithBasicInfoValidator;
@Constraint(validatedBy = CheckIfNationWithBasicInfoValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckIfNationWithBasicInfo {
	public String getNation() default "getNation";
	
	public  String getProvincialLevel() default "getProvincialLevel";
	
	public  String getDistrict() default "getDistrict";
	
	public  String getCommune() default "getCommune";
	
	public String message() default "Commune,District,ProvincialLevel Can't be Null and blank Because nation is not Viet Nam";
	
=======
import giasuomt.demo.location.Validator.CheckIfNationIsVietnamValidator;

@Constraint(validatedBy = CheckIfNationIsVietnamValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckIfNationIsVietnam {

	public String getNation() default "getNation";

//	public String getState() default "getState";

	public String getProvincialLevel() default "getProvincialLevel";

	public String getDistrict() default "getDistrict";

	public String getCommune() default "getCommune";

	public String message() default "Because nation is Viet Nam, Commune,District,ProvincialLevel can't be Null or Blank or Empty";

>>>>>>> Stashed changes:src/main/java/giasuomt/demo/location/Validation/CheckIfNationIsVietnam.java
	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};
}
