package idusw.sbb.maple.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = CoordinateListValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCoordinates {

  String message() default "Not Valid Coordinates.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
